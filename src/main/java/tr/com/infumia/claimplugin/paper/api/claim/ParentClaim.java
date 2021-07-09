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
import tr.com.infumia.claimplugin.paper.api.member.Member;
import tr.com.infumia.claimplugin.paper.api.permission.Permissible;
import tr.com.infumia.infumialib.paper.transformer.serializers.SentTitle;

/**
 * an interface to determine parent claims.
 */
public interface ParentClaim extends Claim, Permissible {

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
   * obtains the enter message.
   *
   * @return enter message.
   */
  @NotNull
  String getEnterMessage();

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
  String getQuitMessage();

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
   * tries to open storage.
   *
   * @param player the player to open.
   */
  void openStorage(@NotNull Player player);

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
   * tries to set home.
   *
   * @param player the player to set home.
   */
  void setHome(@NotNull Player player);

  /**
   * updates if the claim block not exist.
   */
  void updateClaimBlock();
}
