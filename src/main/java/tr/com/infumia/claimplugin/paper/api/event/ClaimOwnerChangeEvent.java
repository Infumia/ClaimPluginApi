package tr.com.infumia.claimplugin.paper.api.event;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.claimplugin.paper.api.claim.ParentClaim;
import tr.com.infumia.claimplugin.paper.api.member.Member;

/**
 * a class that represents claim owner changes events that fire when the claim's owner change.
 */
public final class ClaimOwnerChangeEvent extends ClaimEvent {

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
    this(false, claim, oldOwner, newOwner);
  }

  /**
   * ctor.
   *
   * @param isAsync the is async.
   * @param claim the claim.
   * @param oldOwner the old owner.
   * @param newOwner the new owner.
   */
  public ClaimOwnerChangeEvent(final boolean isAsync, @NotNull final ParentClaim claim, @NotNull final Member oldOwner,
                               @NotNull final Member newOwner) {
    super(isAsync, claim);
    this.oldOwner = oldOwner;
    this.newOwner = newOwner;
  }

  /**
   * changes the new owner with {@link #oldOwner}.
   */
  public void dontChangeOwner() {
    this.setNewOwner(this.getOldOwner());
  }
}
