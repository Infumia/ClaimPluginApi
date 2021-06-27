package tr.com.infumia.claimplugin.paper.api.member;

import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
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
   * creates a new member.
   *
   * @param uniqueId the unique id to create.
   *
   * @return a newly created member.
   */
  @NotNull
  static Member of(@NotNull final UUID uniqueId) {
    return Members.get(uniqueId);
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
  @Nullable
  default Player getAsPlayer() {
    return Bukkit.getPlayer(this.getUniqueId());
  }

  /**
   * obtains the unique id of the member.
   *
   * @return the unique id of the member.
   */
  @NotNull
  UUID getUniqueId();
}
