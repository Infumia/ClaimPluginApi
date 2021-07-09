package tr.com.infumia.claimplugin.paper.api.claim;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tr.com.infumia.claimplugin.paper.api.event.LocationalEvent;
import tr.com.infumia.claimplugin.paper.api.permission.ControlResult;
import tr.com.infumia.infumialib.paper.location.Cuboid;

/**
 * an interface to determine claims.
 *
 * @todo #1:15m Add throws tag for each method if needed.
 */
public interface Claim {

  /**
   * gets all claims.
   *
   * @return all claims.
   */
  @NotNull
  static Collection<ParentClaim> all() {
    return Claims.all();
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
    return Claims.delete(claim);
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
    return Claims.get(location);
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
    return Claims.getByOwner(uniqueId);
  }

  /**
   * gets claim of the player.
   *
   * @param player the player to get.
   *
   * @return claims of the player.
   */
  @NotNull
  static Collection<ParentClaim> getByOwner(@NotNull final OfflinePlayer player) {
    return Claim.getByOwner(player.getUniqueId());
  }

  /**
   * gets claim of the player.
   *
   * @param player the player to get.
   *
   * @return claims of the player.
   */
  @NotNull
  static Collection<ParentClaim> getByOwner(@NotNull final Player player) {
    return Claim.getByOwner(player.getUniqueId());
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
    return Claims.getInvitation(id);
  }

  /**
   * checks if there is a chunk at the location.
   *
   * @param location the location to check.
   *
   * @return {@code true} if there is a chunk at the location.
   */
  static boolean hasClaim(@NotNull final Location location) {
    return Claims.hasClaim(location);
  }

  /**
   * loads claim via unique id.
   *
   * @param uniqueId the unique id to load.
   *
   * @return completable future for claim.
   */
  @NotNull
  static CompletableFuture<@Nullable ParentClaim> load(@NotNull final UUID uniqueId) {
    return Claims.load(uniqueId);
  }

  /**
   * loads all claims.
   *
   * @return all loaded claims.
   */
  @NotNull
  static CompletableFuture<Collection<ParentClaim>> loadAll() {
    return Claims.loadAll();
  }

  /**
   * removes the invitation.
   *
   * @param id the id to remove.
   */
  static void removeInvitation(@NotNull final String id) {
    Claims.removeInvitation(id);
  }

  /**
   * saves the claim.
   *
   * @param claim the claim to save.
   *
   * @return completable future.
   */
  @NotNull
  static CompletableFuture<Void> save(@NotNull final ParentClaim claim) {
    return Claims.save(claim);
  }

  /**
   * saves all claims.
   *
   * @return completable future.
   */
  @NotNull
  static CompletableFuture<Void> saveAll() {
    return Claims.saveAll();
  }

  /**
   * controls all the permissions.
   *
   * @param event the event to control.
   *
   * @return {@code true} if the event passes the control.
   */
  @NotNull
  default ControlResult control(@NotNull final LocationalEvent event) {
    return this.control(event, true);
  }

  /**
   * controls all the permissions.
   *
   * @param event the event to control.
   * @param cancelIfReturnFalse the cancel if return false to control.
   *
   * @return {@code true} if the event passes the control.
   */
  @NotNull
  default ControlResult control(@NotNull final LocationalEvent event, final boolean cancelIfReturnFalse) {
    return this.control(event, null, cancelIfReturnFalse);
  }

  /**
   * controls all the permissions.
   *
   * @param event the event to control.
   * @param actor the actor to control.
   *
   * @return {@code true} if the event passes the control.
   */
  @NotNull
  default ControlResult control(@NotNull final LocationalEvent event, @Nullable final Player actor) {
    return this.control(event, actor, true);
  }

  /**
   * controls all the permissions.
   *
   * @param event the event to control.
   * @param actor the actor to control.
   * @param cancelIfReturnFalse the cancel if return false to control.
   *
   * @return {@code true} if the event passes the control.
   */
  @NotNull
  ControlResult control(@NotNull LocationalEvent event, @Nullable Player actor, final boolean cancelIfReturnFalse);

  /**
   * obtains the cuboid.
   *
   * @return cuboid.
   */
  @NotNull
  Cuboid getCuboid();

  /**
   * sets the maximum location of the claim.
   *
   * @param cuboid the cuboid to set.
   */
  void setCuboid(@NotNull Cuboid cuboid);

  /**
   * obtains the type.
   *
   * @return pe.
   */
  @NotNull
  ClaimType getType();

  /**
   * obtains the unique id of the claim.
   *
   * @return unique id of the claim.
   */
  @NotNull
  UUID getUniqueId();

  /**
   * checks if the location is in the cuboid of the claim.
   *
   * @param location the location to check.
   *
   * @return {@code true} if the location is in the cuboid of the claim.
   */
  boolean isIn(@NotNull Location location);
}
