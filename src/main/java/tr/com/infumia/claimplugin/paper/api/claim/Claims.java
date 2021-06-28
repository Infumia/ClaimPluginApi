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
import lombok.Synchronized;
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
   * the lock.
   */
  private static final Object LOCK = new Object();

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
  @Synchronized("LOCK")
  @NotNull
  static Collection<Claim> all() {
    return Collections.unmodifiableSet(Claims.CLAIMS_SET);
  }

  /**
   * gets claim at the location.
   *
   * @param location the location to get.
   *
   * @return claim at location.
   */
  @Synchronized("LOCK")
  @NotNull
  static Optional<Claim> get(@NotNull final Location location) {
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
  @Synchronized("LOCK")
  @NotNull
  static Collection<Claim> getByOwner(@NotNull final UUID uniqueId) {
    return Claims.CLAIMS_SET.stream()
      .filter(claim -> claim.getOwner().equals(uniqueId))
      .collect(Collectors.toSet());
  }

  /**
   * gets claim via unique id.
   *
   * @param uniqueId the unique id to get.
   *
   * @return completable future for claim.
   */
  @NotNull
  @Synchronized("LOCK")
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
   * saves the claim.
   *
   * @param claim the claim to save.
   */
  @Synchronized("LOCK")
  @NotNull
  static CompletableFuture<Void> save(@NotNull final Claim claim) {
    return Claims.supplyClaim(claim).whenComplete((none, throwable) -> {
      if (throwable != null) {
        throwable.printStackTrace();
      }
      final var uniqueId = claim.getUniqueId();
      if (!Claims.CLAIMS.containsKey(uniqueId)) {
        Claims.CLAIMS.put(uniqueId, claim);
        Claims.CLAIMS_SET.add(claim);
      }
    });
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
