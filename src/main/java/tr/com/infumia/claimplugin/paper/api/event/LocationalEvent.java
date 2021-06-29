package tr.com.infumia.claimplugin.paper.api.event;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockBreakEvent;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.claimplugin.paper.api.claim.Claim;

/**
 * an interface to determine locational events.
 */
public interface LocationalEvent {

  /**
   * creates a locational event from block break event.
   *
   * @param event the event to create.
   *
   * @return locational event.
   */
  @NotNull
  static LocationalEvent of(@NotNull final Claim claim, @NotNull final BlockBreakEvent event) {
    return new Impl(claim, event, event.getBlock().getLocation());
  }

  /**
   * obtains the claim.
   *
   * @return claim.
   */
  @NotNull
  Claim getClaim();

  /**
   * obtains the event.
   *
   * @return event.
   */
  @NotNull
  Event getEvent();

  /**
   * obtains the location.
   *
   * @return location.
   */
  @NotNull
  Location getLocation();

  /**
   * a simple implementation of {@link LocationalEvent}.
   */
  @Getter
  @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
  final class Impl implements LocationalEvent {

    /**
     * the claim.
     */
    @NotNull
    private final Claim claim;

    /**
     * the event.
     */
    @NotNull
    private final Event event;

    /**
     * the location.
     */
    @NotNull
    private final Location location;
  }
}
