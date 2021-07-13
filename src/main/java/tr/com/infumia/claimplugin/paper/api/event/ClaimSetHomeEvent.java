package tr.com.infumia.claimplugin.paper.api.event;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.claimplugin.paper.api.claim.ParentClaim;

/**
 * a class that represents claim home events that fire when someone tries to set home home.
 */
public final class ClaimSetHomeEvent extends ClaimEvent implements Cancellable {

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
   * the location.
   */
  @NotNull
  @Getter
  @Setter
  private Location location;

  /**
   * ctor.
   *
   * @param claim the claim.
   * @param player the player.
   */
  public ClaimSetHomeEvent(@NotNull final ParentClaim claim, @NotNull final Player player) {
    super(claim);
    this.player = player;
    this.location = player.getLocation();
  }
}
