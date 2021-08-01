package tr.com.infumia.claimplugin.paper.api.event;

import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.claimplugin.paper.api.claim.ParentClaim;

/**
 * a class that represents claim post delete events that fire after the claim delete.
 */
public final class ClaimPostDeleteEvent extends ClaimEvent {

  /**
   * the handler list.
   */
  private static final HandlerList handlerList = new HandlerList();

  /**
   * ctor.
   *
   * @param claim the claim.
   */
  public ClaimPostDeleteEvent(@NotNull final ParentClaim claim) {
    super(claim);
  }

  /**
   * the handler list.
   *
   * @return handler list.
   */
  @NotNull
  public static HandlerList getHandlerList() {
    return ClaimPostDeleteEvent.handlerList;
  }

  @NotNull
  @Override
  public HandlerList getHandlers() {
    return ClaimPostDeleteEvent.handlerList;
  }
}
