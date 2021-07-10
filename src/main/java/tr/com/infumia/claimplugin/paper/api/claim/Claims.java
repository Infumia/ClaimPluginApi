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
  private static final Map<UUID, ParentClaim> CLAIMS = new ConcurrentHashMap<>();

  /**
   * the claims.
   */
  private static final Set<ParentClaim> CLAIMS_SET = new HashSet<>();

  /**
   * the invitations.
   * <p>
   * id-player unique id,claim unique id.
   */
  private static final Map<String, Map.Entry<UUID, ParentClaim>> INVITATIONS = new ConcurrentHashMap<>();

  /**
   * the claim serializer.
   */
  @Nullable
  private static ClaimSerializer claimSerializer;

  /**
   * ctor.
   */
  private Claims() {
  }

  /**
   * adds the id to invitation.
   *
   * @param id the id to add.
   * @param player the player to add.
   * @param claim the claim to add.
   */
  static void addInvitation(@NotNull final String id, @NotNull final UUID player,
                            @NotNull final ParentClaim claim) {
    Claims.INVITATIONS.put(id, Map.entry(player, claim));
  }

  /**
   * gets all claims.
   *
   * @return all claims.
   */
  @NotNull
  static Collection<ParentClaim> all() {
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
  static CompletableFuture<Void> delete(@NotNull final ParentClaim claim) {
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
  @NotNull
  static Optional<ParentClaim> get(@NotNull final Location location) {
    return Claims.CLAIMS_SET.stream()
      .filter(claim -> claim.isIn(location))
      .findFirst();
  }

  /**
   * gets claim of the player.
   *
   * @param uniqueId the unique id to get.
   *
   * @return claims of the player.
   */
  @NotNull
  static Collection<ParentClaim> getByOwner(@NotNull final UUID uniqueId) {
    return Claims.CLAIMS_SET.stream()
      .filter(claim -> claim.getOwner().getUniqueId().equals(uniqueId))
      .collect(Collectors.toCollection(HashSet::new));
  }

  /**
   * obtains the invited player.
   *
   * @param id the id to get.
   *
   * @return invited player's unique id.
   */
  @NotNull
  static Optional<Map.Entry<UUID, ParentClaim>> getInvitation(@NotNull final String id) {
    return Optional.ofNullable(Claims.INVITATIONS.get(id));
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
  static CompletableFuture<@Nullable ParentClaim> load(@NotNull final UUID uniqueId) {
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
  static CompletableFuture<Collection<ParentClaim>> loadAll() {
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
   * removes the invitation.
   *
   * @param id the id to remove.
   */
  static void removeInvitation(@NotNull final String id) {
    Claims.INVITATIONS.remove(id);
  }

  /**
   * saves the claim.
   *
   * @param claim the claim to save.
   */
  @NotNull
  static CompletableFuture<Void> save(@NotNull final ParentClaim claim) {
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
  private static CompletableFuture<Void> deleteClaim(@NotNull final ParentClaim claim) {
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
   * sets the claim serializer if it's not set already.
   *
   * @param claimSerializer the claim serializer to set.
   */
  public static void setClaimSerializer(@NotNull final ClaimSerializer claimSerializer) {
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
  private static CompletableFuture<Collection<ParentClaim>> provideAllClaims() {
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
  private static CompletableFuture<@Nullable ParentClaim> provideClaim(@NotNull final UUID uniqueId) {
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
  private static CompletableFuture<Void> supplyAllClaims(@NotNull final Collection<ParentClaim> claims) {
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
  private static CompletableFuture<Void> supplyClaim(@NotNull final ParentClaim claim) {
    return CompletableFuture.runAsync(() -> Claims.getClaimSerializer().save(claim));
  }
}
