package tr.com.infumia.claimplugin.paper.api.event;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.claimplugin.paper.api.claim.Claim;
import tr.com.infumia.claimplugin.paper.api.member.Member;

/**
 * an abstract class that represents claim member events.
 */
public abstract class ClaimMemberEvent extends ClaimEvent {

  /**
   * the member.
   */
  @NotNull
  @Getter
  @Setter
  private Member member;

  /**
   * ctor.
   *
   * @param claim the claim.
   * @param member the member.
   */
  ClaimMemberEvent(final @NotNull Claim claim, @NotNull final Member member) {
    super(claim);
    this.member = member;
  }

  /**
   * ctor.
   *
   * @param isAsync the is async.
   * @param claim the claim.
   * @param member the member.
   */
  ClaimMemberEvent(final boolean isAsync, final @NotNull Claim claim, @NotNull final Member member) {
    super(isAsync, claim);
    this.member = member;
  }
}
