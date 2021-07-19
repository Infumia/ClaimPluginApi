package tr.com.infumia.claimplugin.paper.api.permission;

import java.util.Map;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine permissible.
 *
 * @todo #1:15m Add throws tag for each method if needed.
 */
public interface Permissible {

  /**
   * adds the permission.
   *
   * @param id the id to add.
   */
  default void addPermission(@NotNull final String id) {
    Permissions.get(id).ifPresent(this::addPermission);
  }

  /**
   * adds the permission.
   *
   * @param permission the permission to add.
   */
  default void addPermission(@NotNull final Permission permission) {
    this.addPermission(permission, Permission.Status.ENABLED);
  }

  /**
   * adds the permission.
   *
   * @param permission the permission to add.
   * @param status the status to add.
   */
  void addPermission(@NotNull Permission permission, @NotNull Permission.Status status);

  /**
   * gets the status of the permission.
   *
   * @param permission the permission to get.
   *
   * @return status of the permission.
   */
  @NotNull
  default Optional<Permission.Status> getPermission(@NotNull final Permission permission) {
    return Optional.ofNullable(this.getPermissions().get(permission));
  }

  /**
   * obtains permissions.
   *
   * @return permissions.
   */
  @NotNull
  Map<Permission, Permission.Status> getPermissions();

  /**
   * removes the permission.
   *
   * @param id the id to remove.
   */
  default void removePermission(@NotNull final String id) {
    Permissions.get(id).ifPresent(this::removePermission);
  }

  /**
   * removes the permission.
   *
   * @param permission the permission to remove.
   */
  void removePermission(@NotNull Permission permission);
}
