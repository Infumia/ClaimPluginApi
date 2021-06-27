package tr.com.infumia.claimplugin.paper.api.permission;

import java.util.Optional;
import org.jetbrains.annotations.NotNull;

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
}
