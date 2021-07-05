package tr.com.infumia.claimplugin.paper.api.claim;

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
import java.util.stream.Collectors;
import lombok.Setter;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that contains utility methods for {@link Claim}.
 */
public final class Claims {

  /**
   * the claims.
   */
  private static final Map<UUID, Claim> CLAIMS = new ConcurrentHashMap<>();

  /**
   * the claims.
   */
  private static final Set<Claim> CLAIMS_SET = new HashSet<>();

  /**
   * the claim serializer.
   */
  @Nullable
  @Setter
  private static ClaimSerializer claimSerializer;

  /**
   * ctor.
   */
  private Claims() {
  }

  /**
   * gets all claims.
   *
   * @return all claims.
   */
  @NotNull
  static Collection<Claim> all() {
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
  static CompletableFuture<Void> delete(@NotNull final Claim claim) {
    Claims.CLAIMS_SET.remove(claim);
    Claims.CLAIMS.remove(claim.getUniqueId());
    return Claims.deleteClaim(claim);
  }

  /**
   * gets claim at the location.
   *
   * @param location the location to get.
   *
   * @return claim at location.
   */
  @Nullable
  static Claim get(@NotNull final Location location) {
    return Claims.CLAIMS_SET.stream()
      .filter(claim -> claim.isIn(location))
      .findFirst().orElse(null);
  }

  /**
   * gets claim of the player.
   *
   * @param uniqueId the unique id to get.
   *
   * @return claims of the player.
   */
  @NotNull
  static Collection<Claim> getByOwner(@NotNull final UUID uniqueId) {
    return Claims.CLAIMS_SET.stream()
      .filter(claim -> claim.getOwner().getUniqueId().equals(uniqueId))
      .collect(Collectors.toCollection(HashSet::new));
  }

  /**
   * checks if there is a chunk at the location.
   *
   * @param location the location to check.
   *
   * @return {@code true} if there is a chunk at the location.
   */
  static boolean hasClaim(@NotNull final Location location) {
    return Claims.CLAIMS_SET.stream().anyMatch(claim -> claim.isIn(location));
  }

  /**
   * gets claim via unique id.
   *
   * @param uniqueId the unique id to get.
   *
   * @return completable future for claim.
   */
  @NotNull
  static CompletableFuture<@Nullable Claim> load(@NotNull final UUID uniqueId) {
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
      }
    });
  }

  /**
   * loads all claims.
   *
   * @return all loaded claims.
   */
  @NotNull
  static CompletableFuture<Collection<Claim>> loadAll() {
    return Claims.provideAllClaims().whenComplete((claims, throwable) -> {
      if (throwable != null) {
        throwable.printStackTrace();
      }
      for (final var claim : claims) {
        if (Claims.CLAIMS.containsKey(claim.getUniqueId())) {
          continue;
        }
        Claims.CLAIMS.put(claim.getUniqueId(), claim);
        Claims.CLAIMS_SET.add(claim);
      }
    });
  }

  /**
   * saves the claim.
   *
   * @param claim the claim to save.
   */
  @NotNull
  static CompletableFuture<Void> save(@NotNull final Claim claim) {
    final var uniqueId = claim.getUniqueId();
    if (!Claims.CLAIMS.containsKey(uniqueId)) {
      Claims.CLAIMS.put(uniqueId, claim);
      Claims.CLAIMS_SET.add(claim);
    }
    return Claims.supplyClaim(claim);
  }

  /**
   * saves all claims.
   *
   * @return completable future.
   */
  @NotNull
  static CompletableFuture<Void> saveAll() {
    return Claims.supplyAllClaims(Claims.CLAIMS_SET);
  }

  /**
   * deletes the unique id.
   *
   * @param claim the claim to delete.
   *
   * @return completable future for delete.
   */
  @NotNull
  private static CompletableFuture<Void> deleteClaim(@NotNull final Claim claim) {
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
  private static ClaimSerializer getClaimSerializer() {
    return Objects.requireNonNull(Claims.claimSerializer, "claim serializer");
  }

  /**
   * provides all claims from {@link #claimSerializer}.
   *
   * @return all claims.
   */
  @NotNull
  private static CompletableFuture<Collection<Claim>> provideAllClaims() {
    return CompletableFuture.supplyAsync(() -> Claims.getClaimSerializer().all());
  }

  /**
   * provides claim from {@link #claimSerializer}.
   *
   * @param uniqueId the unique id to provide.
   *
   * @return claim.
   */
  @NotNull
  private static CompletableFuture<@Nullable Claim> provideClaim(@NotNull final UUID uniqueId) {
    return CompletableFuture.supplyAsync(() -> Claims.getClaimSerializer().load(uniqueId));
  }

  /**
   * supplies all the claims to {@link #claimSerializer}.
   *
   * @param claims the claims to supply.
   *
   * @return completable future.
   */
  @NotNull
  private static CompletableFuture<Void> supplyAllClaims(@NotNull final Collection<Claim> claims) {
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
  private static CompletableFuture<Void> supplyClaim(@NotNull final Claim claim) {
    return CompletableFuture.runAsync(() -> Claims.getClaimSerializer().save(claim));
  }
}
