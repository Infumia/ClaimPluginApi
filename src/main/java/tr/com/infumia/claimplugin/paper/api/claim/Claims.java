package tr.com.infumia.claimplugin.paper.api.claim;

import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMaps;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.UtilityClass;
import org.bukkit.Location;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that contains utility methods for {@link Claim}.
 */
@UtilityClass
public class Claims {

  /**
   * the claims by unique id.
   */
  private final Map<UUID, ParentClaim> CLAIMS = new ConcurrentHashMap<>();

  /**
   * the claims.
   */
  private final Set<ParentClaim> CLAIMS_SET = ConcurrentHashMap.newKeySet();

  /**
   * the claim cache by location.
   */
  private final Object2ObjectMap<Location, ParentClaim> CLAIM_CACHE_BY_LOCATION = Object2ObjectMaps.synchronize(
    new Object2ObjectOpenHashMap<>());

  /**
   * the claim cache by owner.
   */
  private final Object2ObjectMap<UUID, Collection<ParentClaim>> CLAIM_CACHE_BY_OWNER =
    Object2ObjectMaps.synchronize(new Object2ObjectOpenHashMap<>());

  /**
   * the invitations.
   * <p>
   * id-player unique id,claim unique id.
   */
  private final Map<String, Map.Entry<UUID, ParentClaim>> INVITATIONS = new ConcurrentHashMap<>();

  /**
   * the cache level.
   */
  @Setter
  @Getter
  private int cacheLevel;

  /**
   * the claim serializer.
   */
  @Nullable
  private ClaimSerializer claimSerializer;

  /**
   * adds the id to invitation.
   *
   * @param id the id to add.
   * @param player the player to add.
   * @param claim the claim to add.
   */
  void addInvitation(@NotNull final String id, @NotNull final UUID player, @NotNull final ParentClaim claim) {
    Claims.INVITATIONS.put(id, Map.entry(player, claim));
  }

  /**
   * gets all claims.
   *
   * @return all claims.
   */
  @NotNull
  Collection<ParentClaim> all() {
    return Collections.unmodifiableSet(Claims.CLAIMS_SET);
  }

  /**
   * deletes the claim from cache and database.
   *
   * @param claim the claim to delete.
   *
   * @return completable future.
   */
  @NotNull
  CompletableFuture<Void> delete(@NotNull final ParentClaim claim) {
    Claims.CLAIMS_SET.remove(claim);
    Claims.CLAIMS.remove(claim.getUniqueId());
    Claims.removeCache(claim);
    claim.getClaimBlockLocation().getBlock().setType(Material.AIR);
    return Claims.deleteClaim(claim);
  }

  /**
   * gets claim at the location.
   *
   * @param uniqueId the unique id to get.
   *
   * @return claim at location.
   */
  @NotNull
  Optional<ParentClaim> get(@NotNull final UUID uniqueId) {
    return Optional.ofNullable(Claims.CLAIMS.get(uniqueId));
  }

  /**
   * gets claim at the location.
   *
   * @param location the location to get.
   *
   * @return claim at location.
   */
  @NotNull
  Optional<ParentClaim> get(@NotNull final Location location) {
    if (Claims.cacheLevel >= 1) {
      final var cache = Claims.CLAIM_CACHE_BY_LOCATION.get(location);
      if (cache != null) {
        return Optional.of(cache);
      }
    }
    for (final var claim : Claims.CLAIMS_SET) {
      if (claim.isIn(location)) {
        Claims.addCache(location, claim);
        return Optional.of(claim);
      }
    }
    return Optional.empty();
  }

  /**
   * gets claim of the player.
   *
   * @param uniqueId the unique id to get.
   * @param location the location to get.
   *
   * @return claims of the player.
   */
  @NotNull
  Optional<ParentClaim> getByOwner(@NotNull final UUID uniqueId, @NotNull final Location location) {
    if (Claims.cacheLevel >= 1) {
      final var cache = Claims.CLAIM_CACHE_BY_LOCATION.get(location);
      if (cache != null && cache.getOwnerAsUniqueId().equals(uniqueId)) {
        return Optional.of(cache);
      }
    }
    for (final var claim : Claims.CLAIMS_SET) {
      final var claimOwner = claim.getOwnerAsUniqueId();
      if (claimOwner.equals(uniqueId) && claim.isIn(location)) {
        Claims.addCache(claim.getClaimBlockLocation(), claim);
        return Optional.of(claim);
      }
    }
    return Optional.empty();
  }

  /**
   * gets claim of the player.
   *
   * @param uniqueId the unique id to get.
   *
   * @return claims of the player.
   */
  @NotNull
  Collection<ParentClaim> getByOwner(@NotNull final UUID uniqueId) {
    if (Claims.cacheLevel >= 1) {
      final var cache = Claims.CLAIM_CACHE_BY_OWNER.get(uniqueId);
      if (cache != null) {
        return cache;
      }
    }
    final var parentClaims = new HashSet<ParentClaim>();
    for (final var claim : Claims.CLAIMS_SET) {
      final var claimOwner = claim.getOwnerAsUniqueId();
      if (claimOwner.equals(uniqueId)) {
        Claims.addCache(claim.getClaimBlockLocation(), claim);
        parentClaims.add(claim);
      }
    }
    return parentClaims;
  }

  /**
   * obtains the invited player.
   *
   * @param id the id to get.
   *
   * @return invited player's unique id.
   */
  @NotNull
  Optional<Map.Entry<UUID, ParentClaim>> getInvitation(@NotNull final String id) {
    return Optional.ofNullable(Claims.INVITATIONS.get(id));
  }

  /**
   * gets claim at the location.
   *
   * @param location the location to get.
   *
   * @return claim at location.
   */
  @NotNull
  Optional<Claim> getOrSub(@NotNull final Location location) {
    if (Claims.cacheLevel >= 1) {
      final var cache = Claims.CLAIM_CACHE_BY_LOCATION.get(location);
      if (cache != null) {
        for (final var subClaim : cache.getSubClaims()) {
          if (subClaim.isIn(location)) {
            return Optional.of(subClaim);
          }
        }
        return Optional.of(cache);
      }
    }
    for (final var claim : Claims.CLAIMS_SET) {
      if (claim.getCuboid().isInXZ(location)) {
        Claims.addCache(location, claim);
        return Optional.of(claim);
      }
      for (final var subClaim : claim.getSubClaims()) {
        if (subClaim.isIn(location)) {
          Claims.addCache(location, claim);
          return Optional.of(subClaim);
        }
      }
    }
    return Optional.empty();
  }

  /**
   * checks if there is a chunk at the location.
   *
   * @param location the location to check.
   *
   * @return {@code true} if there is a chunk at the location.
   */
  boolean hasClaim(@NotNull final Location location) {
    if (Claims.cacheLevel >= 1) {
      if (Claims.CLAIM_CACHE_BY_LOCATION.containsKey(location)) {
        return true;
      }
    }
    for (final var claim : Claims.CLAIMS_SET) {
      if (claim.isIn(location)) {
        Claims.addCache(location, claim);
        return true;
      }
    }
    return false;
  }

  /**
   * gets claim via unique id.
   *
   * @param uniqueId the unique id to get.
   *
   * @return completable future for claim.
   */
  @NotNull
  CompletableFuture<@Nullable ParentClaim> load(@NotNull final UUID uniqueId) {
    if (Claims.CLAIMS.containsKey(uniqueId)) {
      return CompletableFuture.completedFuture(Claims.CLAIMS.get(uniqueId));
    }
    return Claims.provideClaim(uniqueId).whenComplete((claim, throwable) -> {
      if (throwable != null) {
        throwable.printStackTrace();
      }
      if (claim != null) {
        Claims.CLAIMS.put(claim.getUniqueId(), claim);
        Claims.CLAIMS_SET.add(claim);
        Claims.addCache(claim.getClaimBlockLocation(), claim);
      }
    });
  }

  /**
   * loads all claims.
   *
   * @return all loaded claims.
   */
  @NotNull
  CompletableFuture<Collection<ParentClaim>> loadAll() {
    return Claims.provideAllClaims().whenComplete((claims, throwable) -> {
      if (throwable != null) {
        throwable.printStackTrace();
      }
      for (final var claim : claims) {
        if (!Claims.CLAIMS.containsKey(claim.getUniqueId())) {
          Claims.CLAIMS.put(claim.getUniqueId(), claim);
          Claims.CLAIMS_SET.add(claim);
          Claims.addCache(claim.getClaimBlockLocation(), claim);
        }
      }
    });
  }

  /**
   * removes the invitation.
   *
   * @param id the id to remove.
   */
  void removeInvitation(@NotNull final String id) {
    Claims.INVITATIONS.remove(id);
  }

  /**
   * saves the claim.
   *
   * @param claim the claim to save.
   */
  @NotNull
  CompletableFuture<Void> save(@NotNull final ParentClaim claim) {
    final var uniqueId = claim.getUniqueId();
    if (!Claims.CLAIMS.containsKey(uniqueId)) {
      Claims.CLAIMS.put(uniqueId, claim);
      Claims.CLAIMS_SET.add(claim);
      Claims.addCache(claim.getClaimBlockLocation(), claim);
    }
    return Claims.supplyClaim(claim);
  }

  /**
   * saves all claims.
   *
   * @return completable future.
   */
  @NotNull
  CompletableFuture<Void> saveAll() {
    return Claims.supplyAllClaims(Claims.CLAIMS_SET);
  }

  /**
   * adds location and claim to {@link #CLAIM_CACHE_BY_LOCATION}.
   * <p>
   * adds the cache only if the cache level equals to 1 or bigger.
   *
   * @param location the location to add.
   * @param claim the claim to add.
   */
  private void addCache(@NotNull final Location location, @NotNull final ParentClaim claim) {
    if (Claims.cacheLevel < 1) {
      return;
    }
    Claims.CLAIM_CACHE_BY_LOCATION.putIfAbsent(location, claim);
    final var owner = claim.getOwnerAsUniqueId();
    Claims.CLAIM_CACHE_BY_OWNER.compute(owner, (uuid, parentClaims) -> {
      if (parentClaims == null) {
        return Arrays.asList(claim);
      }
      if (!parentClaims.contains(claim)) {
        parentClaims.add(claim);
      }
      return parentClaims;
    });
  }

  /**
   * deletes the unique id.
   *
   * @param claim the claim to delete.
   *
   * @return completable future for delete.
   */
  @NotNull
  private CompletableFuture<Void> deleteClaim(@NotNull final ParentClaim claim) {
    return CompletableFuture.runAsync(() -> Claims.getClaimSerializer().delete(claim));
  }

  /**
   * gets the claim serializer.
   *
   * @return claim serializer.
   *
   * @throws NullPointerException if {@link #claimSerializer} is {@code null}.
   */
  @NotNull
  private ClaimSerializer getClaimSerializer() {
    return Objects.requireNonNull(Claims.claimSerializer, "claim serializer");
  }

  /**
   * sets the claim serializer if it's not set already.
   *
   * @param claimSerializer the claim serializer to set.
   */
  public void setClaimSerializer(@NotNull final ClaimSerializer claimSerializer) {
    if (Claims.claimSerializer == null) {
      Claims.claimSerializer = claimSerializer;
    }
  }

  /**
   * provides all claims from {@link #claimSerializer}.
   *
   * @return all claims.
   */
  @NotNull
  private CompletableFuture<Collection<ParentClaim>> provideAllClaims() {
    return CompletableFuture.supplyAsync(() -> Claims.getClaimSerializer().loadAll());
  }

  /**
   * provides claim from {@link #claimSerializer}.
   *
   * @param uniqueId the unique id to provide.
   *
   * @return claim.
   */
  @NotNull
  private CompletableFuture<@Nullable ParentClaim> provideClaim(@NotNull final UUID uniqueId) {
    return CompletableFuture.supplyAsync(() -> Claims.getClaimSerializer().load(uniqueId));
  }

  /**
   * removes the cache.
   *
   * @param claim the claim to remove.
   */
  private void removeCache(@NotNull final ParentClaim claim) {
    Claims.CLAIM_CACHE_BY_LOCATION.entrySet().removeIf(entry -> claim.equals(entry.getValue()));
    Claims.CLAIM_CACHE_BY_OWNER.entrySet().removeIf(entry -> claim.getOwnerAsUniqueId().equals(entry.getKey()));
  }

  /**
   * supplies all the claims to {@link #claimSerializer}.
   *
   * @param claims the claims to supply.
   *
   * @return completable future.
   */
  @NotNull
  private CompletableFuture<Void> supplyAllClaims(@NotNull final Collection<ParentClaim> claims) {
    return CompletableFuture.runAsync(() -> Claims.getClaimSerializer().saveAll(claims));
  }

  /**
   * supplies the claim to {@link #claimSerializer}.
   *
   * @param claim the claim to supply.
   *
   * @return completable future.
   */
  @NotNull
  private CompletableFuture<Void> supplyClaim(@NotNull final ParentClaim claim) {
    return CompletableFuture.runAsync(() -> Claims.getClaimSerializer().save(claim));
  }
}
