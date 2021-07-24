package tr.com.infumia.claimplugin.paper.api.event;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.claimplugin.paper.api.claim.ParentClaim;

/**
 * a class that represents claim pre delete events that fire before the claim delete.
 */
public final class ClaimPreDeleteEvent extends ClaimEvent implements Cancellable {

  /**
   * the handler list.
   */
  private static final HandlerList handlerList = new HandlerList();

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
   */
  public ClaimPreDeleteEvent(@NotNull final ParentClaim claim) {
    super(claim);
  }

  /**
   * the handler list.
   *
   * @return handler list.
   */
  @NotNull
  public static HandlerList getHandlerList() {
    return ClaimPreDeleteEvent.handlerList;
  }

  @NotNull
  @Override
  public final HandlerList getHandlers() {
    return ClaimPreDeleteEvent.handlerList;
  }
}
