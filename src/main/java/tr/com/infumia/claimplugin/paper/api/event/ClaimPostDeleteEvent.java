package tr.com.infumia.claimplugin.paper.api.event;

import lombok.Getter;
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
  @Getter
  private static final HandlerList handlerList = new HandlerList();

  /**
   * ctor.
   *
   * @param claim the claim.
   */
  public ClaimPostDeleteEvent(@NotNull final ParentClaim claim) {
    super(claim);
  }

  @NotNull
  @Override
  public HandlerList getHandlers() {
    return ClaimPostDeleteEvent.handlerList;
  }
}
