package tr.com.infumia.claimplugin.paper.api.event;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.claimplugin.paper.api.claim.ParentClaim;
import tr.com.infumia.claimplugin.paper.api.member.Member;

/**
 * a class that represents claim owner changes events that fire when the claim's owner change.
 */
public final class ClaimOwnerChangeEvent extends ClaimEvent {

  /**
   * the handler list.
   */
  private static final HandlerList handlerList = new HandlerList();

  /**
   * the old owner.
   */
  @NotNull
  @Getter
  private final Member oldOwner;

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

  /**
   * the handler list.
   *
   * @return handler list.
   */
  @NotNull
  public static HandlerList getHandlerList() {
    return ClaimOwnerChangeEvent.handlerList;
  }

  @NotNull
  @Override
  public final HandlerList getHandlers() {
    return ClaimOwnerChangeEvent.handlerList;
  }

  /**
   * changes the new owner with {@link #oldOwner}.
   */
  public void dontChangeOwner() {
    this.setNewOwner(this.getOldOwner());
  }
}
