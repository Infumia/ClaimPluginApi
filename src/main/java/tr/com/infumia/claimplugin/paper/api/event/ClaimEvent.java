package tr.com.infumia.claimplugin.paper.api.event;

import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.claimplugin.paper.api.claim.Claim;

/**
 * an abstract class that helps to create custom events.
 */
abstract class ClaimEvent extends Event {

  /**
   * the handler list.
   */
  private static final HandlerList handlerList = new HandlerList();

  /**
   * the claim.
   */
  @NotNull
  @Getter
  private final Claim claim;

  /**
   * ctor.
   *
   * @param claim the claim.
   */
  ClaimEvent(@NotNull final Claim claim) {
    this.claim = claim;
  }

  /**
   * ctor.
   *
   * @param isAsync the is async.
   * @param claim the claim.
   */
  ClaimEvent(final boolean isAsync, @NotNull final Claim claim) {
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

  @NotNull
  @Override
  public final HandlerList getHandlers() {
    return ClaimEvent.handlerList;
  }
}
