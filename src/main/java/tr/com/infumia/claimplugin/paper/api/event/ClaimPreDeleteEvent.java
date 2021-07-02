package tr.com.infumia.claimplugin.paper.api.event;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Cancellable;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.claimplugin.paper.api.claim.Claim;

/**
 * a class that represents claim pre delete events that fire before the claim delete.
 */
public final class ClaimPreDeleteEvent extends ClaimEvent implements Cancellable {

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
  public ClaimPreDeleteEvent(@NotNull final Claim claim) {
    super(claim);
  }
}
