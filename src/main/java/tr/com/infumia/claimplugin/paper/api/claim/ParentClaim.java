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
import tr.com.infumia.claimplugin.paper.api.event.ClaimAddMemberEvent;
import tr.com.infumia.claimplugin.paper.api.event.ClaimDeleteHomeEvent;
import tr.com.infumia.claimplugin.paper.api.event.ClaimInviteMemberEvent;
import tr.com.infumia.claimplugin.paper.api.event.ClaimOwnerChangeEvent;
import tr.com.infumia.claimplugin.paper.api.event.ClaimPostDeleteEvent;
import tr.com.infumia.claimplugin.paper.api.event.ClaimPreDeleteEvent;
import tr.com.infumia.claimplugin.paper.api.event.ClaimRemoveMemberEvent;
import tr.com.infumia.claimplugin.paper.api.home.Home;
import tr.com.infumia.claimplugin.paper.api.member.Member;
import tr.com.infumia.claimplugin.paper.api.message.ClaimMessage;
import tr.com.infumia.claimplugin.paper.api.permission.Permissible;
import tr.com.infumia.claimplugin.paper.api.storage.Storage;
import tr.com.infumia.infumialib.time.Times;

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
   * @param member the member to add.
   */
  void addMember(@NotNull Member member);

  /**
   * adds the member to the claim.
   *
   * @param member the member to add.
   */
  default boolean addMemberWithEvent(@NotNull final UUID member) {
    return this.addMemberWithEvent(Member.member(member));
  }

  /**
   * adds the member to the claim.
   *
   * @param member the member to add.
   */
  default boolean addMemberWithEvent(@NotNull final Member member) {
    final var event = new ClaimAddMemberEvent(this, member);
    if (event.callEvent()) {
      this.addMember(event.getMember());
      return true;
    }
    return false;
  }

  /**
   * adds the sub claim.
   *
   * @param subClaim the sub claim to add.
   */
  void addSubClaim(@NotNull Claim subClaim);

  /**
   * decreases the expire time.
   */
  void decreaseExpireTime();

  /**
   * deletes the claim.
   */
  default void delete() {
    Claim.delete(this);
  }

  /**
   * deletes the claim.
   *
   * @return {@code true} if the claim deleted successfully.
   */
  default boolean deleteWithEvent() {
    if (new ClaimPreDeleteEvent(this).callEvent()) {
      this.delete();
      new ClaimPostDeleteEvent(this).callEvent();
      return true;
    }
    return false;
  }

  /**
   * sends enter message/title to the player.
   *
   * @param player the player to send.
   */
  void enterClaim(Player player);

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
   * obtains the claim message.
   *
   * @return claim message.
   */
  @NotNull
  ClaimMessage getClaimMessage();

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
   * obtains the expire time by times.
   *
   * @return expire time by times.
   */
  default long[] getExpireTimeByTimes() {
    return Times.getTimeDifference(this.getExpireTime());
  }

  /**
   * obtains the home limit.
   *
   * @return home limit.
   */
  long getHomeLimit();

  /**
   * sets the home limit.
   *
   * @param homeLimit the home limit to set.
   */
  void setHomeLimit(long homeLimit);

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
   * obtains the member limit.
   *
   * @return member limit.
   */
  long getMemberLimit();

  /**
   * sets the member limit.
   *
   * @param memberLimit the member limit to set.
   */
  void setMemberLimit(long memberLimit);

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
   * obtains the storage.
   *
   * @return storage.
   */
  @NotNull
  Storage getStorage();

  /**
   * obtains the sub claims.
   *
   * @return sub claims.
   */
  @NotNull
  Collection<Claim> getSubClaims();

  /**
   * invites the player to become a member of the claim.
   *
   * @param invite the invite to invite.
   */
  void invite(@NotNull Invite invite);

  /**
   * invites the player to become a member of the claim.
   *
   * @param invite the invite to invite.
   *
   * @return {@code true} if the invite succeed.
   */
  default boolean inviteWithEvent(@NotNull final Invite invite) {
    final var event = new ClaimInviteMemberEvent(this, invite);
    if (event.callEvent()) {
      this.invite(invite);
      return true;
    }
    return false;
  }

  /**
   * checks if block at the location is a claim block.
   *
   * @param location the location to check.
   *
   * @return {@code true} if block at the location is a claim block.
   */
  boolean isClaimBlock(@NotNull Location location);

  /**
   * checks if the player is the owner of the claim or a member.
   *
   * @param uniqueId the unique id to check.
   *
   * @return {@code true} if the player is a part of the claim.
   */
  default boolean isOwnerOrMember(@NotNull final UUID uniqueId) {
    return this.getOwnerAsUniqueId().equals(uniqueId) ||
      this.getMember(uniqueId).isPresent();
  }

  /**
   * checks if the player is the owner of the claim or a member.
   *
   * @param player the player to check.
   *
   * @return {@code true} if the player is a part of the claim.
   */
  default boolean isOwnerOrMember(@NotNull final Player player) {
    return this.isOwnerOrMember(player.getUniqueId());
  }

  /**
   * sends quit message/title to the player.
   *
   * @param player the player to send.
   */
  void quitClaim(Player player);

  /**
   * removes the home.
   *
   * @param home the home to remove.
   */
  void removeHome(@NotNull Home home);

  /**
   * removes the home.
   *
   * @param home the home to remove.
   * @param remover the remover to remove.
   *
   * @return {@code true} if the home removed successfully.
   */
  default boolean removeHomeWithEvent(@NotNull final Home home, @NotNull final Player remover) {
    final var claimDeleteHomeEvent = new ClaimDeleteHomeEvent(this, home, remover);
    if (claimDeleteHomeEvent.callEvent()) {
      claimDeleteHomeEvent.getClaim().removeHome(claimDeleteHomeEvent.getHome());
      return true;
    }
    return false;
  }

  /**
   * removes the member from the claim.
   *
   * @param member the member to remove.
   */
  void removeMember(@NotNull Member member);

  /**
   * removes the member from the claim.
   *
   * @param member the member to remove.
   * @param kicker the kicker to remove.
   *
   * @return {@code true} if the member kicked successfully.
   */
  default boolean removeMemberWithEvent(@NotNull final UUID member, @NotNull final Player kicker) {
    return this.removeMemberWithEvent(Member.member(member), kicker);
  }

  /**
   * removes the member from the claim.
   *
   * @param member the member to remove.
   * @param kicker the kicker to remove.
   *
   * @return {@code true} if the member kicked successfully.
   */
  default boolean removeMemberWithEvent(@NotNull final Member member, @NotNull final Player kicker) {
    final var event = new ClaimRemoveMemberEvent(this, member, kicker);
    if (event.callEvent()) {
      this.removeMember(member);
      return true;
    }
    return false;
  }

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
   * sets the owner.
   *
   * @param owner the owner to set.
   * @param changer the changer to set.
   */
  default boolean setOwnerWithEvent(@NotNull final Member owner, @NotNull final Player changer) {
    if (this.removeMemberWithEvent(owner, changer)) {
      final var event = new ClaimOwnerChangeEvent(this, this.getOwner(), owner);
      if (event.callEvent()) {
        this.setOwner(event.getNewOwner());
        return true;
      }
    }
    return false;
  }
}
