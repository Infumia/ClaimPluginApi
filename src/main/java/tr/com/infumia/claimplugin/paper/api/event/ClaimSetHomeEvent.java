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
 * a class that represents claim home events that fire when someone tries to set home home.
 */
public final class ClaimSetHomeEvent extends ClaimEvent implements Cancellable {

  /**
   * the handler list.
   */
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
   * the location.
   */
  @NotNull
  @Getter
  @Setter
  private Home home;

  /**
   * ctor.
   *
   * @param claim the claim.
   * @param player the player.
   * @param home the home.
   */
  public ClaimSetHomeEvent(@NotNull final ParentClaim claim, @NotNull final Player player,
                           @NotNull final Home home) {
    super(claim);
    this.player = player;
    this.home = home;
  }

  /**
   * the handler list.
   *
   * @return handler list.
   */
  @NotNull
  public static HandlerList getHandlerList() {
    return ClaimSetHomeEvent.handlerList;
  }

  @NotNull
  @Override
  public HandlerList getHandlers() {
    return ClaimSetHomeEvent.handlerList;
  }
}
