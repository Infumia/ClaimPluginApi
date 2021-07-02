package tr.com.infumia.claimplugin.paper.api.claim;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine homes.
 */
public interface Home {

  /**
   * creates a simple home instance.
   *
   * @param id the id to create.
   * @param name the name to create.
   * @param location the location to create.
   *
   * @return a newly created home instance.
   */
  @NotNull
  static Home of(@NotNull final UUID id, @NotNull final String name, @NotNull final Location location) {
    return new Impl(id, location, name);
  }

  /**
   * obtains the id.
   *
   * @return id.
   */
  @NotNull
  UUID getId();

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
  @ToString
  @EqualsAndHashCode
  @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
  final class Impl implements Home {

    /**
     * the id.
     */
    @NotNull
    private final UUID id;

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
