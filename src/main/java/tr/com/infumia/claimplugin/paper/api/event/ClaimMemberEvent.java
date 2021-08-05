package tr.com.infumia.claimplugin.paper.api.event;

import org.jetbrains.annotations.NotNull;
import tr.com.infumia.claimplugin.paper.api.claim.ParentClaim;
import tr.com.infumia.claimplugin.paper.api.member.Member;

/**
 * an abstract class that represents claim member events.
 */
abstract class ClaimMemberEvent extends ClaimEvent {

  /**
   * the member.
   */
  @NotNull
  private Member member;

  /**
   * ctor.
   *
   * @param claim the claim.
   * @param member the member.
   */
  ClaimMemberEvent(@NotNull final ParentClaim claim, @NotNull final Member member) {
    super(claim);
    this.member = member;
  }

  /**
   * obtains the member.
   *
   * @return member.
   */
  @NotNull
  public final Member getMember() {
    return this.member;
  }

  /**
   * sets the member.
   *
   * @param member the member to set.
   */
  public final void setMember(@NotNull final Member member) {
    this.member = member;
  }
}
