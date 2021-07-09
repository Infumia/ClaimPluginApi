package tr.com.infumia.claimplugin.paper.api.event;

import org.jetbrains.annotations.NotNull;
import tr.com.infumia.claimplugin.paper.api.claim.ParentClaim;
import tr.com.infumia.claimplugin.paper.api.member.Member;

/**
 * a class that represents claim remove member events that fire when a member remove from a claim.
 */
public final class ClaimRemoveMemberEvent extends ClaimMemberEvent {

  /**
   * ctor.
   *
   * @param claim the claim.
   * @param member the member.
   */
  public ClaimRemoveMemberEvent(@NotNull final ParentClaim claim, @NotNull final Member member) {
    this(false, claim, member);
  }

  /**
   * ctor.
   *
   * @param isAsync the is async.
   * @param claim the claim.
   * @param member the member.
   */
  public ClaimRemoveMemberEvent(final boolean isAsync, @NotNull final ParentClaim claim, @NotNull final Member member) {
    super(isAsync, claim, member);
  }
}
