package tr.com.infumia.claimplugin.paper.api.permission;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.jetbrains.annotations.NotNull;

/**
 * a class that contains default permissions for Claim plugin.
 */
final class Permissions {

  /**
   * the permissions.
   */
  private static final Map<String, Permission> PERMISSIONS = new ConcurrentHashMap<>();

  /**
   * ctor.
   */
  private Permissions() {
  }

  /**
   * gets the permission by id.
   *
   * @param id the id to get.
   *
   * @return permission.
   */
  @NotNull
  static Optional<Permission> get(@NotNull final String id) {
    return Optional.ofNullable(Permissions.PERMISSIONS.get(id));
  }

  /**
   * registers the permission.
   *
   * @param permission the permission to register.
   */
  static void register(@NotNull final Permission permission) {
    Permissions.PERMISSIONS.put(permission.getId(), permission);
  }
}
