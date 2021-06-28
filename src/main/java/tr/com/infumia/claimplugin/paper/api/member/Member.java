package tr.com.infumia.claimplugin.paper.api.member;

import java.util.Objects;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tr.com.infumia.claimplugin.paper.api.permission.ControlResult;
import tr.com.infumia.claimplugin.paper.api.permission.Permissible;

/**
 * an interface to determine claim members.
 */
public interface Member extends Permissible {

  /**
   * creates a new member.
   *
   * @param player the player to create.
   *
   * @return a newly created member.
   */
  @NotNull
  static Member of(@NotNull final OfflinePlayer player) {
    return Member.of(player.getUniqueId());
  }

  /**
   * creates a new member.
   *
   * @param player the player to create.
   *
   * @return a newly created member.
   */
  @NotNull
  static Member of(@NotNull final Player player) {
    return Member.of(player.getUniqueId());
  }

  /**
   * creates the member via unique id.
   *
   * @param uniqueId the unique id to get.
   *
   * @return a newly created member instance.
   */
  @NotNull
  static Member of(@NotNull final UUID uniqueId) {
    return Members.create(uniqueId);
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
  ControlResult control(@NotNull Event event, final boolean cancelIfReturnFalse);

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
  @Nullable
  default Player getAsPlayer() {
    return Bukkit.getPlayer(this.getUniqueId());
  }

  /**
   * obtains the member as player.
   *
   * @return member as player.
   */
  @NotNull
  default Player getAsPlayerOrThrow() {
    return Objects.requireNonNull(this.getAsPlayer(), String.format("Player %s not found!", this.getUniqueId()));
  }

  /**
   * obtains the unique id of the member.
   *
   * @return the unique id of the member.
   */
  @NotNull
  UUID getUniqueId();
}
