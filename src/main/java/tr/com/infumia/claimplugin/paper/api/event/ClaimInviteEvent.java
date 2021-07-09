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
public final class ClaimInviteEvent extends ClaimEvent implements Cancellable {

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
   * @param player the player.
   * @param id the id.
   */
  public ClaimInviteEvent(@NotNull final ParentClaim claim, @NotNull final Player player, @NotNull final String id) {
    this(false, claim, player, id
    );
  }

  /**
   * ctor.
   *
   * @param isAsync the is async.
   * @param claim the claim.
   * @param player the player.
   * @param id the id.
   */
  public ClaimInviteEvent(final boolean isAsync, @NotNull final ParentClaim claim, @NotNull final Player player,
                          @NotNull final String id) {
    super(isAsync, claim);
    this.player = player;
    this.id = id;
  }
}
