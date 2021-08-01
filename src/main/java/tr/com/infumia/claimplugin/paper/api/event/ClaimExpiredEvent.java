package tr.com.infumia.claimplugin.paper.api.event;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.claimplugin.paper.api.claim.ParentClaim;

/**
 * a class that represents claim expire events that fire when a claim expired.
 */
public final class ClaimExpiredEvent extends ClaimEvent implements Cancellable {

  /**
   * the handler list.
   */
  @Getter
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
  public ClaimExpiredEvent(@NotNull final ParentClaim claim) {
    super(claim);
  }

  @NotNull
  @Override
  public HandlerList getHandlers() {
    return ClaimExpiredEvent.handlerList;
  }
}
