package tr.com.infumia.claimplugin.paper.api.event;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Cancellable;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.claimplugin.paper.api.claim.Claim;

/**
 * a class that represents claim post delete events that fire after the claim delete.
 */
public final class ClaimPostDeleteEvent extends ClaimEvent {

  /**
   * ctor.
   *
   * @param claim the claim.
   */
  public ClaimPostDeleteEvent(@NotNull final Claim claim) {
    super(claim);
  }
}
