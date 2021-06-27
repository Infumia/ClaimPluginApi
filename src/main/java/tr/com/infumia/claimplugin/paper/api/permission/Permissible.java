package tr.com.infumia.claimplugin.paper.api.permission;

import java.util.Collection;
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
  void addPermission(@NotNull Permission permission);

  /**
   * obtains permissions.
   *
   * @return permissions.
   */
  @NotNull
  Collection<Permission> getPermissions();

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
