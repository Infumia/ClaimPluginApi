package tr.com.infumia.claimplugin.paper.api.claim;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tr.com.infumia.claimplugin.paper.api.event.ClaimAddMemberEvent;
import tr.com.infumia.claimplugin.paper.api.event.ClaimDeleteHomeEvent;
import tr.com.infumia.claimplugin.paper.api.event.ClaimInviteMemberEvent;
import tr.com.infumia.claimplugin.paper.api.event.ClaimOwnerChangeEvent;
import tr.com.infumia.claimplugin.paper.api.event.ClaimPostDeleteEvent;
import tr.com.infumia.claimplugin.paper.api.event.ClaimPreDeleteEvent;
import tr.com.infumia.claimplugin.paper.api.event.ClaimRemoveMemberEvent;
import tr.com.infumia.claimplugin.paper.api.event.ClaimSetHomeEvent;
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
   * calls the given event then, if it's succeed runs the consumer.
   *
   * @param event the event to call.
   * @param consumer the consumer to call.
   * @param <E> type of the event.
   *
   * @return {@code true} if the event called successfully.
   */
  static <E extends Event> boolean callEvent(@NotNull final E event, @NotNull final Consumer<E> consumer) {
    if (event.callEvent()) {
      consumer.accept(event);
      return true;
    }
    return false;
  }

  /**
   * adds the home.
   *
   * @param home the home to add.
   */
  void addHome(@NotNull Home home);

  /**
   * adds the home.
   *
   * @param adder the adder to add.
   *
   * @return {@code true} if home added successfully.
   */
  default boolean addHomeWithEvent(@NotNull final Player adder) {
    return this.addHomeWithEvent(adder, event -> {
    });
  }

  /**
   * adds the home.
   *
   * @param adder the adder to add.
   * @param consumer the consumer to add.
   *
   * @return {@code true} if home added successfully.
   */
  default boolean addHomeWithEvent(@NotNull final Player adder, @NotNull final Consumer<ClaimSetHomeEvent> consumer) {
    return this.addHomeWithEvent(adder.getLocation(), adder, consumer);
  }

  /**
   * adds the home.
   *
   * @param location the location to add.
   * @param adder the adder to add.
   *
   * @return {@code true} if home added successfully.
   */
  default boolean addHomeWithEvent(@NotNull final Location location, @NotNull final Player adder) {
    return this.addHomeWithEvent(location, adder, event -> {
    });
  }

  /**
   * adds the home.
   *
   * @param location the location to add.
   * @param adder the adder to add.
   * @param consumer the consumer to add.
   *
   * @return {@code true} if home added successfully.
   */
  default boolean addHomeWithEvent(@NotNull final Location location, @NotNull final Player adder,
                                   @NotNull final Consumer<ClaimSetHomeEvent> consumer) {
    final var id = UUID.randomUUID();
    return this.addHomeWithEvent(id, id.toString(), location, adder, consumer);
  }

  /**
   * adds the home.
   *
   * @param name the name to add.
   * @param location the location to add.
   * @param adder the adder to add.
   *
   * @return {@code true} if home added successfully.
   */
  default boolean addHomeWithEvent(@NotNull final String name, @NotNull final Location location,
                                   @NotNull final Player adder) {
    return this.addHomeWithEvent(name, location, adder, event -> {
    });
  }

  /**
   * adds the home.
   *
   * @param name the name to add.
   * @param location the location to add.
   * @param adder the adder to add.
   * @param consumer the consumer to add.
   *
   * @return {@code true} if home added successfully.
   */
  default boolean addHomeWithEvent(@NotNull final String name, @NotNull final Location location,
                                   @NotNull final Player adder, @NotNull final Consumer<ClaimSetHomeEvent> consumer) {
    return this.addHomeWithEvent(UUID.randomUUID(), name, location, adder, consumer);
  }

  /**
   * adds the home.
   *
   * @param id the id to add.
   * @param name the name to add.
   * @param location the location to add.
   * @param adder the adder to add.
   *
   * @return {@code true} if home added successfully.
   */
  default boolean addHomeWithEvent(@NotNull final UUID id, @NotNull final String name, @NotNull final Location location,
                                   @NotNull final Player adder) {
    return this.addHomeWithEvent(id, name, location, adder, event -> {
    });
  }

  /**
   * adds the home.
   *
   * @param id the id to add.
   * @param name the name to add.
   * @param location the location to add.
   * @param adder the adder to add.
   * @param consumer the consumer to add.
   *
   * @return {@code true} if home added successfully.
   */
  default boolean addHomeWithEvent(@NotNull final UUID id, @NotNull final String name, @NotNull final Location location,
                                   @NotNull final Player adder, @NotNull final Consumer<ClaimSetHomeEvent> consumer) {
    return this.addHomeWithEvent(Home.of(id, name, location), adder);
  }

  /**
   * adds the home.
   *
   * @param home the home to add.
   * @param adder the adder to add.
   *
   * @return {@code true} if the home added successfully.
   */
  default boolean addHomeWithEvent(@NotNull final Home home, @NotNull final Player adder) {
    return ParentClaim.callEvent(new ClaimSetHomeEvent(this, home, adder), event ->
      event.getClaim().addHome(event.getHome()));
  }

  /**
   * adds the home.
   *
   * @param home the home to add.
   * @param adder the adder to add.
   * @param consumer the consumer to add.
   *
   * @return {@code true} if the home added successfully.
   */
  default boolean addHomeWithEvent(@NotNull final Home home, @NotNull final Player adder,
                                   @NotNull final Consumer<ClaimSetHomeEvent> consumer) {
    return ParentClaim.callEvent(new ClaimSetHomeEvent(this, home, adder), event -> {
      event.getClaim().addHome(event.getHome());
      consumer.accept(event);
    });
  }

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
   *
   * @return {@code this} if the adding member succeed.
   */
  default boolean addMemberWithEvent(@NotNull final UUID member) {
    return this.addMemberWithEvent(member, event -> {
    });
  }

  /**
   * adds the member to the claim.
   *
   * @param member the member to add.
   * @param consumer the consumer to add.
   *
   * @return {@code this} if the adding member succeed.
   */
  default boolean addMemberWithEvent(@NotNull final UUID member,
                                     @NotNull final Consumer<ClaimAddMemberEvent> consumer) {
    return this.addMemberWithEvent(Member.member(member), consumer);
  }

  /**
   * adds the member to the claim.
   *
   * @param member the member to add.
   *
   * @return {@code true} if member added successfully.
   */
  default boolean addMemberWithEvent(@NotNull final Member member) {
    return this.addMemberWithEvent(member, event -> {
    });
  }

  /**
   * adds the member to the claim.
   *
   * @param member the member to add.
   * @param consumer the consumer to add.
   *
   * @return {@code true} if member added successfully.
   */
  default boolean addMemberWithEvent(@NotNull final Member member,
                                     @NotNull final Consumer<ClaimAddMemberEvent> consumer) {
    return ParentClaim.callEvent(new ClaimAddMemberEvent(this, member), event -> {
      event.getClaim().addMember(event.getMember());
      consumer.accept(event);
    });
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
    return ParentClaim.callEvent(new ClaimPreDeleteEvent(this), e -> {
      this.delete();
      new ClaimPostDeleteEvent(this).callEvent();
    });
  }

  /**
   * sends enter message/title to the player.
   *
   * @param player the player to send.
   */
  default void enterClaim(@NotNull final Player player) {
    final var claimMessage = this.getClaimMessage();
    if (claimMessage.isEnterQuitMessageEnabled()) {
      player.sendMessage(claimMessage.getEnterMessage().build(
        Map.entry("%claim_name%", this::getName),
        Map.entry("%claim_member_count%", () -> 1 + this.getMembers().size())));
    }
    if (claimMessage.isEnterQuitTitleEnabled()) {
      claimMessage.getEnterTitle().send(player,
        Map.entry("%claim_name%", this::getName),
        Map.entry("%claim_member_count%", () -> 1 + this.getMembers().size()));
    }
  }

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
    return this.inviteWithEvent(invite, event -> {
    });
  }

  /**
   * invites the player to become a member of the claim.
   *
   * @param invite the invite to invite.
   * @param consumer the consumer to invite.
   *
   * @return {@code true} if the invite succeed.
   */
  default boolean inviteWithEvent(@NotNull final Invite invite,
                                  @NotNull final Consumer<ClaimInviteMemberEvent> consumer) {
    return ParentClaim.callEvent(new ClaimInviteMemberEvent(this, invite), event -> {
      event.getClaim().invite(event.getInvite());
      consumer.accept(event);
    });
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
  default void quitClaim(@NotNull final Player player) {
    final var claimMessage = this.getClaimMessage();
    if (this.getClaimMessage().isEnterQuitMessageEnabled()) {
      player.sendMessage(claimMessage.getQuitMessage().build(
        Map.entry("%claim_name%", this::getName),
        Map.entry("%claim_member_count%", () -> 1 + this.getMembers().size())));
    }
    if (claimMessage.isEnterQuitTitleEnabled()) {
      claimMessage.getQuitTitle().send(player,
        Map.entry("%claim_name%", this::getName),
        Map.entry("%claim_member_count%", () -> 1 + this.getMembers().size()));
    }
  }

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
    return this.removeHomeWithEvent(home, remover, event -> {
    });
  }

  /**
   * removes the home.
   *
   * @param home the home to remove.
   * @param remover the remover to remove.
   * @param consumer the consumer to remove.
   *
   * @return {@code true} if the home removed successfully.
   */
  default boolean removeHomeWithEvent(@NotNull final Home home, @NotNull final Player remover,
                                      @NotNull final Consumer<ClaimDeleteHomeEvent> consumer) {
    return ParentClaim.callEvent(new ClaimDeleteHomeEvent(this, home, remover), event -> {
      event.getClaim().removeHome(event.getHome());
      consumer.accept(event);
    });
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
    return this.removeMemberWithEvent(member, kicker, event -> {
    });
  }

  /**
   * removes the member from the claim.
   *
   * @param member the member to remove.
   * @param kicker the kicker to remove.
   * @param consumer the consumer to remove.
   *
   * @return {@code true} if the member kicked successfully.
   */
  default boolean removeMemberWithEvent(@NotNull final UUID member, @NotNull final Player kicker,
                                        @NotNull final Consumer<ClaimRemoveMemberEvent> consumer) {
    return this.removeMemberWithEvent(Member.member(member), kicker, consumer);
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
    return this.removeMemberWithEvent(member, kicker, event -> {
    });
  }

  /**
   * removes the member from the claim.
   *
   * @param member the member to remove.
   * @param kicker the kicker to remove.
   * @param consumer the consumer to remove.
   *
   * @return {@code true} if the member kicked successfully.
   */
  default boolean removeMemberWithEvent(@NotNull final Member member, @NotNull final Player kicker,
                                        @NotNull final Consumer<ClaimRemoveMemberEvent> consumer) {
    return ParentClaim.callEvent(new ClaimRemoveMemberEvent(this, member, kicker), event -> {
      event.getClaim().removeMember(event.getMember());
      consumer.accept(event);
    });
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
   *
   * @return {@code true} if the changing owner succeed.
   */
  default boolean setOwnerWithEvent(@NotNull final Member owner, @NotNull final Player changer) {
    return this.setOwnerWithEvent(owner, changer, event -> {
    }, event -> {
    });
  }

  /**
   * sets the owner.
   *
   * @param owner the owner to set.
   * @param changer the changer to set.
   * @param consumer the consumer to set.
   *
   * @return {@code true} if the changing owner succeed.
   */
  default boolean setOwnerWithEvent(@NotNull final Member owner, @NotNull final Player changer,
                                    @NotNull final Consumer<ClaimOwnerChangeEvent> consumer) {
    return this.setOwnerWithEvent(owner, changer, event -> {
    }, consumer);
  }

  /**
   * sets the owner.
   *
   * @param owner the owner to set.
   * @param changer the changer to set.
   * @param claimRemoveMemberEventConsumer the claim remove member event consumer to set.
   * @param claimOwnerChangeEventConsumer the claim owner change event consumer to set.
   *
   * @return {@code true} if the changing owner succeed.
   */
  default boolean setOwnerWithEvent(@NotNull final Member owner, @NotNull final Player changer,
                                    @NotNull final Consumer<ClaimRemoveMemberEvent> claimRemoveMemberEventConsumer,
                                    @NotNull final Consumer<ClaimOwnerChangeEvent> claimOwnerChangeEventConsumer) {
    if (this.removeMemberWithEvent(owner, changer, claimRemoveMemberEventConsumer)) {
      return ParentClaim.callEvent(new ClaimOwnerChangeEvent(this, this.getOwner(), owner), event -> {
        event.getClaim().setOwner(event.getNewOwner());
        claimOwnerChangeEventConsumer.accept(event);
      });
    }
    return false;
  }

  /**
   * teleports the player to the home.
   *
   * @param home the home to teleport.
   * @param player the player to teleport.
   */
  default void teleportHome(@NotNull final Home home, @NotNull final Player player) {
    final var location = home.getLocation();
    try {
      player.teleportAsync(location);
    } catch (final Exception e) {
      player.teleport(location);
    }
  }

  /**
   * teleports the player to the home.
   *
   * @param home the home to teleport.
   * @param player the player to teleport.
   *
   * @return {@code true} if the teleportation succeed.
   */
  boolean teleportHomeWithEvent(@NotNull final Home home, @NotNull final Player player);
}
