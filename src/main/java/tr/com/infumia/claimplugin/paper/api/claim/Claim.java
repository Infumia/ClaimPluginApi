package tr.com.infumia.claimplugin.paper.api.claim;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tr.com.infumia.claimplugin.paper.api.event.LocationalEvent;
import tr.com.infumia.claimplugin.paper.api.member.Member;
import tr.com.infumia.claimplugin.paper.api.permission.ControlResult;
import tr.com.infumia.claimplugin.paper.api.permission.Permissible;
import tr.com.infumia.infumialib.paper.location.Cuboid;
import tr.com.infumia.infumialib.paper.transformer.serializers.SentTitle;

/**
 * an interface to determine claims.
 *
 * @todo #1:15m Add throws tag for each method if needed.
 */
public interface Claim extends Permissible {

  /**
   * gets all claims.
   *
   * @return all claims.
   */
  @NotNull
  static Collection<Claim> all() {
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
  static CompletableFuture<Void> delete(@NotNull final Claim claim) {
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
  static Optional<Claim> get(@NotNull final Location location) {
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
  static Collection<Claim> getByOwner(@NotNull final UUID uniqueId) {
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
  static Collection<Claim> getByOwner(@NotNull final OfflinePlayer player) {
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
  static Collection<Claim> getByOwner(@NotNull final Player player) {
    return Claim.getByOwner(player.getUniqueId());
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
  static CompletableFuture<@Nullable Claim> load(@NotNull final UUID uniqueId) {
    return Claims.load(uniqueId);
  }

  /**
   * loads all claims.
   *
   * @return all loaded claims.
   */
  @NotNull
  static CompletableFuture<Collection<Claim>> loadAll() {
    return Claims.loadAll();
  }

  /**
   * saves the claim.
   *
   * @param claim the claim to save.
   *
   * @return completable future.
   */
  @NotNull
  static CompletableFuture<Void> save(@NotNull final Claim claim) {
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
   * adds the home.
   *
   * @param location the location to add.
   */
  default void addHome(@NotNull final Location location) {
    final var id = UUID.randomUUID();
    this.addHome(id, id.toString(), location);
  }

  /**
   * adds the home.
   *
   * @param name the name to add.
   * @param location the location to add.
   */
  default void addHome(@NotNull final String name, @NotNull final Location location) {
    this.addHome(UUID.randomUUID(), name, location);
  }

  /**
   * adds the home.
   *
   * @param id the id to add.
   * @param name the name to add.
   * @param location the location to add.
   */
  default void addHome(@NotNull final UUID id, @NotNull final String name, @NotNull final Location location) {
    this.addHome(Home.of(id, name, location));
  }

  /**
   * adds the home.
   *
   * @param home the home to add.
   */
  void addHome(@NotNull Home home);

  /**
   * adds the member to the claim.
   *
   * @param uniqueId the unique id to add.
   */
  default void addMember(@NotNull final UUID uniqueId) {
    this.addMember(Member.member(uniqueId));
  }

  /**
   * adds the member to the claim.
   *
   * @param member the member to add.
   */
  void addMember(@NotNull Member member);

  /**
   * adds the sub claim.
   *
   * @param subClaim the sub claim to add.
   */
  void addSubClaim(@NotNull Claim subClaim);

  /**
   * checks if the claim can expire.
   *
   * @return {@code true} if the claim can expire.
   */
  default boolean canExpire() {
    return this.getExpireTime() >= 0;
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
   * decreases the expire time.
   */
  void decreaseExpireTime();

  /**
   * deletes the claim.
   */
  void delete();

  /**
   * obtains the claim block location.
   *
   * @return claim block location.
   */
  @NotNull
  Location getClaimBlockLocation();

  /**
   * sets the claim block location.
   *
   * @param location the location to set.
   */
  void setClaimBlockLocation(@NotNull Location location);

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
   * obtains the enter message.
   *
   * @return enter message.
   */
  @NotNull
  SentTitle getEnterMessage();

  /**
   * sets enter message.
   *
   * @param message the message to set.
   */
  void setEnterMessage(@NotNull String message);

  /**
   * obtains the enter title.
   *
   * @return enter title.
   */
  @NotNull
  SentTitle getEnterTitle();

  /**
   * sets enter title.
   *
   * @param title the title to set.
   */
  void setEnterTitle(@NotNull SentTitle title);

  /**
   * obtains the expire time.
   *
   * @return expire time.
   */
  long getExpireTime();

  /**
   * sets the expire time.
   *
   * @param expireTime expire time to set.
   */
  void setExpireTime(long expireTime);

  /**
   * obtains the homes.
   *
   * @return homes.
   */
  @NotNull
  Collection<Home> getHomes();

  /**
   * gets the member via unique id.
   *
   * @param uniqueId the unique id to get.
   *
   * @return member.
   */
  @NotNull
  Optional<Member> getMember(@NotNull UUID uniqueId);

  /**
   * obtains the members of the claim.
   *
   * @return members of the claim.
   */
  @NotNull
  Collection<Member> getMembers();

  /**
   * obtains the name.
   * <p>
   * the name will use in management menu which include all claims of the player.
   *
   * @return name.
   */
  @NotNull
  String getName();

  /**
   * sets the name.
   *
   * @param name the name to set.
   */
  void setName(@NotNull String name);

  /**
   * obtains the owner.
   *
   * @return owner.
   */
  @NotNull
  Member getOwner();

  /**
   * sets the owner.
   *
   * @param owner the owner to set.
   */
  void setOwner(@NotNull Member owner);

  /**
   * obtains the owner as offline player.
   *
   * @return owner as offline player.
   */
  @NotNull
  default OfflinePlayer getOwnerAsOfflinePlayer() {
    return Bukkit.getOfflinePlayer(this.getOwnerAsUniqueId());
  }

  /**
   * obtains the owner as player.
   *
   * @return owner as player.
   */
  @Nullable
  default Player getOwnerAsPlayer() {
    return Bukkit.getPlayer(this.getOwnerAsUniqueId());
  }

  /**
   * obtains the owner's unique id.
   *
   * @return owner's unique id.
   */
  @NotNull
  default UUID getOwnerAsUniqueId() {
    return this.getOwner().getUniqueId();
  }

  /**
   * obtains the quit message.
   *
   * @return quit message.
   */
  @NotNull
  SentTitle getQuitMessage();

  /**
   * sets quit message.
   *
   * @param message the message to set.
   */
  void setQuitMessage(@NotNull String message);

  /**
   * obtains the quit title.
   *
   * @return quit title.
   */
  @NotNull
  SentTitle getQuitTitle();

  /**
   * sets quit title.
   *
   * @param title the title to set.
   */
  void setQuitTitle(@NotNull SentTitle title);

  /**
   * obtains the sub claims.
   *
   * @return sub claims.
   */
  @NotNull
  Collection<Claim> getSubClaims();

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
   * checks if block at the location is a claim block.
   *
   * @param location the location to check.
   *
   * @return {@code true} if block at the location is a claim block.
   */
  boolean isClaimBlock(@NotNull Location location);

  /**
   * obtains enter quit message enabled.
   *
   * @return enter quit message enabled.
   */
  boolean isEnterQuitMessageEnabled();

  /**
   * sets enter quit message enabled.
   *
   * @param enterQuitMessageEnabled the enter quit title message to set.
   */
  void setEnterQuitMessageEnabled(boolean enterQuitMessageEnabled);

  /**
   * obtains enter quit title enabled.
   *
   * @return enter quit title enabled.
   */
  boolean isEnterQuitTitleEnabled();

  /**
   * sets enter quit title enabled.
   *
   * @param enterQuitTitleEnabled the enter quit title enabled to set.
   */
  void setEnterQuitTitleEnabled(boolean enterQuitTitleEnabled);

  /**
   * checks if the location is in the cuboid of the claim.
   *
   * @param location the location to check.
   *
   * @return {@code true} if the location is in the cuboid of the claim.
   */
  boolean isIn(@NotNull Location location);

  /**
   * removes the home.
   *
   * @param home the home to remove.
   */
  void removeHome(@NotNull Home home);

  /**
   * removes the member from the claim.
   *
   * @param uniqueId the unique id to remove.
   */
  default void removeMember(@NotNull final UUID uniqueId) {
    this.removeMember(Member.member(uniqueId));
  }

  /**
   * removes the member from the claim.
   *
   * @param member the member to remove.
   */
  void removeMember(@NotNull Member member);

  /**
   * removes the sub claim.
   *
   * @param subClaim the sub claim to remove.
   */
  void removeSubClaim(@NotNull Claim subClaim);

  /**
   * saves the claim.
   *
   * @return completable future.
   */
  @NotNull
  default CompletableFuture<Void> save() {
    return Claim.save(this);
  }

  /**
   * updates if the claim block not exist.
   */
  void updateClaimBlock();
}
