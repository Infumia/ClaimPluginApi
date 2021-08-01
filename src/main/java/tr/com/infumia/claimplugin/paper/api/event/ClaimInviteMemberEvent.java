package tr.com.infumia.claimplugin.paper.api.event;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.claimplugin.paper.api.claim.Invite;
import tr.com.infumia.claimplugin.paper.api.claim.ParentClaim;

/**
 * a class that represents claim home events that fire when claim owners tries to invite someone to their claims.
 */
public final class ClaimInviteMemberEvent extends ClaimEvent implements Cancellable {

  /**
   * the handler list.
   */
  @Getter
  private static final HandlerList handlerList = new HandlerList();

  /**
   * the cancelled.
   */
  @Getter
  @Setter
  private boolean cancelled;

  /**
   * the invite.
   */
  @Setter
  @Getter
  @NotNull
  private Invite invite;

  /**
   * ctor.
   *
   * @param claim the claim.
   * @param invite the invite.
   */
  public ClaimInviteMemberEvent(@NotNull final ParentClaim claim, @NotNull final Invite invite) {
    super(claim);
    this.invite = invite;
  }

  @NotNull
  @Override
  public HandlerList getHandlers() {
    return ClaimInviteMemberEvent.handlerList;
  }
}
