package tr.com.infumia.claimplugin.paper.api.event;

import org.jetbrains.annotations.NotNull;
import tr.com.infumia.claimplugin.paper.api.claim.ParentClaim;

/**
 * a class that represents claim post delete events that fire after the claim delete.
 */
public final class ClaimPostDeleteEvent extends ClaimEvent {

  /**
   * ctor.
   *
   * @param claim the claim.
   */
  public ClaimPostDeleteEvent(@NotNull final ParentClaim claim) {
    super(true, claim);
  }
}
