package tr.com.infumia.claimplugin.paper.api.event;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.claimplugin.paper.api.claim.ParentClaim;
import tr.com.infumia.claimplugin.paper.api.home.Home;

/**
 * a class that represents claim post teleport home events that fire when the teleporting home delay done.
 */
public final class ClaimPostTeleportHomeEvent extends ClaimHomeEvent implements Cancellable {

  /**
   * the handler list.
   */
  @Getter
  private static final HandlerList handlerList = new HandlerList();

  /**
   * the player.
   */
  @NotNull
  @Getter
  private final Player player;

  /**
   * the cancelled.
   */
  @Getter
  @Setter
  private boolean cancelled;

  /**
   * ctor.
   *
   * @param claim the claim.
   * @param home the home.
   * @param player the player.
   */
  public ClaimPostTeleportHomeEvent(@NotNull final ParentClaim claim, @NotNull final Home home,
                                    @NotNull final Player player) {
    super(claim, home);
    this.player = player;
  }

  @NotNull
  @Override
  public HandlerList getHandlers() {
    return ClaimPostTeleportHomeEvent.handlerList;
  }
}
