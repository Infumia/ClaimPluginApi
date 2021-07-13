package tr.com.infumia.claimplugin.paper.api.event;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Cancellable;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.claimplugin.paper.api.claim.ParentClaim;
import tr.com.infumia.claimplugin.paper.api.member.Member;

/**
 * a class that represents claim add member events that fire when a member join to a claim.
 */
public final class ClaimAddMemberEvent extends ClaimMemberEvent implements Cancellable {

  /**
   * the cancelled.
   */
  @Getter
  @Setter
  private boolean cancelled;

  /**
   * ctor.
   *
   * @param claim the claim.
   * @param member the member.
   */
  public ClaimAddMemberEvent(@NotNull final ParentClaim claim, @NotNull final Member member) {
    super(claim, member);
  }
}
