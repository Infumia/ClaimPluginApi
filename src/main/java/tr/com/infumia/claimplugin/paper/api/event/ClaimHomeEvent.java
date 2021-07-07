package tr.com.infumia.claimplugin.paper.api.event;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.claimplugin.paper.api.claim.Claim;
import tr.com.infumia.claimplugin.paper.api.claim.Home;

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
   * the home.
   */
  @NotNull
  @Getter
  @Setter
  private Home home;

  /**
   * ctor.
   *
   * @param claim the claim.
   * @param player the player.
   */
  public ClaimHomeEvent(@NotNull final Claim claim, @NotNull final Player player, @NotNull final Home home) {
    super(claim);
    this.player = player;
    this.home = home;
  }

  /**
   * ctor.
   *
   * @param isAsync the is async.
   * @param claim the claim.
   * @param player the player.
   */
  public ClaimHomeEvent(final boolean isAsync, @NotNull final Claim claim, @NotNull final Player player,
                        @NotNull final Home home) {
    super(isAsync, claim);
    this.player = player;
    this.home = home;
  }
}
