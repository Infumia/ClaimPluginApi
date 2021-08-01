package tr.com.infumia.claimplugin.paper.api.event;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.claimplugin.paper.api.claim.ParentClaim;
import tr.com.infumia.claimplugin.paper.api.member.Member;

/**
 * a class that represents claim owner changes events that fire when the claim's owner change.
 */
public final class ClaimOwnerChangeEvent extends ClaimEvent implements Cancellable {

  /**
   * the handler list.
   */
  @Getter
  private static final HandlerList handlerList = new HandlerList();

  /**
   * the old owner.
   */
  @NotNull
  @Getter
  private final Member oldOwner;

  /**
   * the cancelled.
   */
  @Setter
  @Getter
  private boolean cancelled;

  /**
   * the new owner.
   */
  @NotNull
  @Getter
  @Setter
  private Member newOwner;

  /**
   * ctor.
   *
   * @param claim the claim.
   * @param oldOwner the old owner.
   * @param newOwner the new owner.
   */
  public ClaimOwnerChangeEvent(@NotNull final ParentClaim claim, @NotNull final Member oldOwner,
                               @NotNull final Member newOwner) {
    super(claim);
    this.oldOwner = oldOwner;
    this.newOwner = newOwner;
  }

  @NotNull
  @Override
  public HandlerList getHandlers() {
    return ClaimOwnerChangeEvent.handlerList;
  }
}
