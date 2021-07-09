package tr.com.infumia.claimplugin.paper.api.event;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.claimplugin.paper.api.claim.ParentClaim;

/**
 * a class that represents claim home events that fire when someone tries to open storage of the claims.
 */
public final class ClaimOpenStorageEvent extends ClaimEvent implements Cancellable {

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
  public ClaimOpenStorageEvent(@NotNull final ParentClaim claim, @NotNull final Player player) {
    this(false, claim, player);
  }

  /**
   * ctor.
   *
   * @param isAsync the is async.
   * @param claim the claim.
   * @param player the player.
   */
  public ClaimOpenStorageEvent(final boolean isAsync, @NotNull final ParentClaim claim, @NotNull final Player player) {
    super(isAsync, claim);
    this.player = player;
  }
}
