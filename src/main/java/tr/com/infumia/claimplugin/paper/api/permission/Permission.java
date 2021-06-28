package tr.com.infumia.claimplugin.paper.api.permission;

import java.util.Arrays;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.claimplugin.paper.api.event.LocationalEvent;

/**
 * an interface to determine permissions.
 */
public interface Permission {

  /**
   * gets permission via id.
   *
   * @param id the id to get.
   *
   * @return permission.
   */
  @NotNull
  static Optional<Permission> of(@NotNull final String id) {
    return Permissions.get(id);
  }

  /**
   * registers the permission.
   *
   * @param permission the permission to register.
   */
  static void register(@NotNull final Permission permission) {
    Permissions.register(permission);
  }

  /**
   * controls all the permissions.
   *
   * @param event the event to control.
   * @param cancelIfFailed the cancel if failed to control.
   *
   * @return {@code true} if the event passes the control.
   */
  @NotNull
  ControlResult control(@NotNull LocationalEvent event, final boolean cancelIfFailed);

  /**
   * obtains the id.
   *
   * @return id.
   */
  @NotNull
  String getId();

  /**
   * checks if the permission is global.
   *
   * @return {@code true} if the permission is global.
   */
  boolean isGlobal();

  /**
   * registers the permission.
   */
  default void register() {
    Permission.register(this);
  }

  /**
   * an enum class that contains status of a permission.
   *
   * @todo #1:15m Add ROLE after adding a Role system for members. The ROLE status basically checks member's role.
   */
  @Getter
  @RequiredArgsConstructor(access = AccessLevel.PACKAGE)
  enum Status {
    /**
     * the enabled.
     */
    ENABLED("on"),
    /**
     * the disabled.
     */
    DISABLED("off");

    /**
     * the id.
     */
    @NotNull
    private final String id;

    /**
     * gets status by id, unless {@link #ENABLED}.
     *
     * @param id the id to get.
     *
     * @return status.
     */
    @NotNull
    public static Status getById(@NotNull final String id) {
      return Arrays.stream(Status.values())
        .filter(status -> status.getId().equalsIgnoreCase(id))
        .findFirst()
        .orElse(Status.ENABLED);
    }
  }
}
