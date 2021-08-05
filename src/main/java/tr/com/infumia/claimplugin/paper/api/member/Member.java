package tr.com.infumia.claimplugin.paper.api.member;

import java.util.Optional;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.claimplugin.paper.api.permission.Control;
import tr.com.infumia.claimplugin.paper.api.permission.Permissible;

/**
 * an interface to determine claim members.
 */
public interface Member extends Permissible, Control {

  /**
   * creates a new member.
   *
   * @param player the player to create.
   *
   * @return a newly created member.
   */
  @NotNull
  static Member member(@NotNull final OfflinePlayer player) {
    return Member.member(player.getUniqueId());
  }

  /**
   * creates a new member.
   *
   * @param player the player to create.
   *
   * @return a newly created member.
   */
  @NotNull
  static Member member(@NotNull final Player player) {
    return Member.member(player.getUniqueId());
  }

  /**
   * creates the member via unique id.
   *
   * @param uniqueId the unique id to get.
   *
   * @return a newly created member instance.
   */
  @NotNull
  static Member member(@NotNull final UUID uniqueId) {
    return Members.createMember(uniqueId);
  }

  /**
   * creates a new owner.
   *
   * @param player the player to create.
   *
   * @return a newly created member.
   */
  @NotNull
  static Member owner(@NotNull final OfflinePlayer player) {
    return Member.owner(player.getUniqueId());
  }

  /**
   * creates a new owner.
   *
   * @param player the player to create.
   *
   * @return a newly created member.
   */
  @NotNull
  static Member owner(@NotNull final Player player) {
    return Member.owner(player.getUniqueId());
  }

  /**
   * creates the owner via member.
   *
   * @param member the member to get.
   *
   * @return a newly created member instance.
   */
  @NotNull
  static Member owner(@NotNull final Member member) {
    return Member.owner(member.getUniqueId());
  }

  /**
   * creates the owner via unique id.
   *
   * @param uniqueId the unique id to get.
   *
   * @return a newly created member instance.
   */
  @NotNull
  static Member owner(@NotNull final UUID uniqueId) {
    return Members.createOwner(uniqueId);
  }

  /**
   * obtains the member as offline player.
   *
   * @return member as offline player.
   */
  @NotNull
  default OfflinePlayer getAsOfflinePlayer() {
    return Bukkit.getOfflinePlayer(this.getUniqueId());
  }

  /**
   * obtains the member as player.
   *
   * @return member as player.
   */
  @NotNull
  default Optional<Player> getAsPlayer() {
    return Optional.ofNullable(Bukkit.getPlayer(this.getUniqueId()));
  }

  /**
   * obtains the member as player.
   *
   * @return member as player.
   */
  @NotNull
  default Player getAsPlayerOrThrow() {
    return this.getAsPlayer().orElseThrow(() ->
      new IllegalStateException(String.format("Player called %s not found!", this.getUniqueId())));
  }

  /**
   * obtains name or unique id of the member.
   *
   * @return name or uniqeu id.
   */
  @NotNull
  default String getName() {
    final var name = this.getAsOfflinePlayer().getName();
    return name == null ? this.getUniqueId().toString() : name;
  }

  /**
   * obtains the unique id of the member.
   *
   * @return the unique id of the member.
   */
  @NotNull
  UUID getUniqueId();
}
