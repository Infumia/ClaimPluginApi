package tr.com.infumia.claimplugin.paper.api.event;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine locational events.
 */
public interface LocationalEvent {

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
