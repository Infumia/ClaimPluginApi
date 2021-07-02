package tr.com.infumia.claimplugin.paper.api.event;

import org.jetbrains.annotations.NotNull;
import tr.com.infumia.claimplugin.paper.api.claim.Claim;
import tr.com.infumia.claimplugin.paper.api.member.Member;

/**
 * a class that represents claim add member events that fire when a member join to a claim.
 */
public final class ClaimAddMemberEvent extends ClaimMemberEvent {

  /**
   * ctor.
   *
   * @param claim the claim.
   * @param member the member.
   */
  public ClaimAddMemberEvent(final @NotNull Claim claim, final @NotNull Member member) {
    super(claim, member);
  }

  /**
   * ctor.
   *
   * @param isAsync the is async.
   * @param claim the claim.
   * @param member the member.
   */
  public ClaimAddMemberEvent(final boolean isAsync, final @NotNull Claim claim, final @NotNull Member member) {
    super(isAsync, claim, member);
  }
}