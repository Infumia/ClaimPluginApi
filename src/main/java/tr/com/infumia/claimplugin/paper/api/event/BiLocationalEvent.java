package tr.com.infumia.claimplugin.paper.api.event;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine bi locational events.
 */
public interface BiLocationalEvent extends LocationalEvent {

  /**
   * obtains the second location.
   *
   * @return second location.
   */
  @NotNull
  Location getSecondLocation();

  /**
   * a simple implementation of {@link BiLocationalEvent}.
   */
  @Getter
  @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
  final class Impl implements BiLocationalEvent {

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

    /**
     * the second location.
     */
    @NotNull
    private final Location secondLocation;
  }
}
