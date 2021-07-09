package tr.com.infumia.claimplugin.paper.api.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.claimplugin.paper.api.claim.ParentClaim;

/**
 * an abstract class that helps to create custom events.
 */
public abstract class ClaimEvent extends Event {

  /**
   * the handler list.
   */
  private static final HandlerList handlerList = new HandlerList();

  /**
   * the claim.
   */
  @NotNull
  private final ParentClaim claim;

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
   * the handler list.
   *
   * @return handler list.
   */
  public static HandlerList getHandlerList() {
    return ClaimEvent.handlerList;
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

  @NotNull
  @Override
  public final HandlerList getHandlers() {
    return ClaimEvent.handlerList;
  }
}
