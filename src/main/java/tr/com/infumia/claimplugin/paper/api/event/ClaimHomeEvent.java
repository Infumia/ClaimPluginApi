package tr.com.infumia.claimplugin.paper.api.event;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.claimplugin.paper.api.claim.Claim;

/**
 * a class that represents claim home events that fire when someone use home for teleporting to claims.
 */
public final class ClaimHomeEvent extends ClaimEvent implements Cancellable {

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
   * ctor.
   *
   * @param claim the claim.
   * @param player the player.
   */
  public ClaimHomeEvent(final @NotNull Claim claim, @NotNull final Player player) {
    super(claim);
    this.player = player;
  }

  /**
   * ctor.
   *
   * @param isAsync the is async.
   * @param claim the claim.
   * @param player the player.
   */
  public ClaimHomeEvent(final boolean isAsync, final @NotNull Claim claim, @NotNull final Player player) {
    super(isAsync, claim);
    this.player = player;
  }
}
