package tr.com.infumia.claimplugin.paper.api.event;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.claimplugin.paper.api.claim.Claim;
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
  public ClaimOwnerChangeEvent(@NotNull final Claim claim,
                               @NotNull final Member oldOwner, @NotNull final Member newOwner) {
    super(claim);
    this.oldOwner = oldOwner;
    this.newOwner = newOwner;
  }
}
