package tr.com.infumia.claimplugin.paper.api.event;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Cancellable;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.claimplugin.paper.api.claim.ParentClaim;

/**
 * a class that represents claim expire events that fire when a claim expired.
 * <p>
 * the event calls in async.
 */
public final class ClaimExpireEvent extends ClaimEvent implements Cancellable {

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
  public ClaimExpireEvent(@NotNull final ParentClaim claim) {
    super(true, claim);
  }
}
