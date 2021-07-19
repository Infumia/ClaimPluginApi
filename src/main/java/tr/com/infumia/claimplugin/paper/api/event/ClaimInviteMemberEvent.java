package tr.com.infumia.claimplugin.paper.api.event;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.claimplugin.paper.api.claim.ParentClaim;

/**
 * a class that represents claim home events that fire when claim owners tries to invite someone to their claims.
 */
public final class ClaimInviteMemberEvent extends ClaimEvent implements Cancellable {

  /**
   * the inviter.
   */
  @NotNull
  @Getter
  private final Player inviter;

  /**
   * the player.
   */
  @NotNull
  @Getter
  private final Player player;

  /**
   * the cancelled.
   */
  @Getter
  @Setter
  private boolean cancelled;

  /**
   * the id.
   */
  @NotNull
  @Getter
  @Setter
  private String id;

  /**
   * ctor.
   *
   * @param claim the claim.
   * @param inviter the player.
   * @param player the player.
   * @param id the id.
   */
  public ClaimInviteMemberEvent(@NotNull final ParentClaim claim, @NotNull final Player inviter,
                                @NotNull final Player player, @NotNull final String id) {
    super(claim);
    this.inviter = inviter;
    this.player = player;
    this.id = id;
  }
}
