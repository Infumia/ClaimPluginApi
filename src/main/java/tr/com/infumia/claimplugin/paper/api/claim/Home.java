package tr.com.infumia.claimplugin.paper.api.claim;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine homes.
 */
public interface Home {

  /**
   * creates a simple home instance.
   *
   * @param name the name to create.
   * @param location the location to create.
   *
   * @return a newly created home instance.
   */
  @NotNull
  static Home of(@NotNull final String name, @NotNull final Location location) {
    return new Impl(location, name);
  }

  /**
   * obtains the location.
   *
   * @return location.
   */
  @NotNull
  Location getLocation();

  /**
   * sets the location.
   *
   * @param location the location to set.
   */
  void setLocation(@NotNull Location location);

  /**
   * obtains the name.
   *
   * @return name.
   */
  @NotNull
  String getName();

  /**
   * sets the name.
   *
   * @param name the name to set.
   */
  void setName(@NotNull String name);

  /**
   * a simple implementation of {@link Home}.
   */
  @Getter
  @Setter
  @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
  final class Impl implements Home {

    /**
     * the location.
     */
    @NotNull
    private Location location;

    /**
     * the name.
     */
    @NotNull
    private String name;
  }
}
