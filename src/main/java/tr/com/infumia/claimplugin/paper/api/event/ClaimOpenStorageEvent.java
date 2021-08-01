package tr.com.infumia.claimplugin.paper.api.event;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.claimplugin.paper.api.claim.ParentClaim;

/**
 * a class that represents claim home events that fire when someone tries to open storage of the claims.
 */
public final class ClaimOpenStorageEvent extends ClaimEvent implements Cancellable {

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
   * ctor.
   *
   * @param claim the claim.
   * @param player the player.
   */
  public ClaimOpenStorageEvent(@NotNull final ParentClaim claim, @NotNull final Player player) {
    super(claim);
    this.player = player;
  }

  /**
   * the handler list.
   *
   * @return handler list.
   */
  @NotNull
  public static HandlerList getHandlerList() {
    return ClaimOpenStorageEvent.handlerList;
  }

  @NotNull
  @Override
  public HandlerList getHandlers() {
    return ClaimOpenStorageEvent.handlerList;
  }
}
