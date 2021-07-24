package tr.com.infumia.claimplugin.paper.api.event;

import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.claimplugin.paper.api.claim.ParentClaim;

/**
 * an abstract class that helps to create custom events.
 */
public abstract class ClaimEvent extends Event {

  /**
   * the claim.
   */
  @NotNull
  private final ParentClaim claim;

  /**
   * ctor.
   *
   * @param claim the claim.
   */
  ClaimEvent(@NotNull final ParentClaim claim) {
    this.claim = claim;
  }

  /**
   * ctor.
   *
   * @param isAsync the is async.
   * @param claim the claim.
   */
  ClaimEvent(final boolean isAsync, @NotNull final ParentClaim claim) {
    super(isAsync);
    this.claim = claim;
  }

  /**
   * obtains the claim.
   *
   * @return claim.
   */
  @NotNull
  public final ParentClaim getClaim() {
    return this.claim;
  }
}
