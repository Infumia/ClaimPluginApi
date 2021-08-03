package tr.com.infumia.claimplugin.paper.api.permission;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

/**
 * a class that contains default permissions for Claim plugin.
 */
@UtilityClass
class Permissions {

  /**
   * the actions.
   */
  private final Collection<Action> ACTIONS = new HashSet<>();

  /**
   * the permissions.
   */
  private final Map<String, Permission> PERMISSIONS = new ConcurrentHashMap<>();

  /**
   * obtains all registered permissions.
   *
   * @return all registered permissions
   */
  @NotNull
  Collection<Permission> all() {
    return Permissions.PERMISSIONS.values();
  }

  /**
   * gets all the actions.
   *
   * @return actions.
   */
  @NotNull
  Collection<Action> allActions() {
    return Collections.unmodifiableCollection(Permissions.ACTIONS);
  }

  /**
   * gets the permission by id.
   *
   * @param id the id to get.
   *
   * @return permission.
   */
  @NotNull
  Optional<Permission> get(@NotNull final String id) {
    return Optional.ofNullable(Permissions.PERMISSIONS.get(id));
  }

  /**
   * obtains global permissions.
   *
   * @return global permission.
   */
  @NotNull
  Collection<Permission> getGlobalPermissions() {
    final var set = new HashSet<Permission>();
    for (final var permission : Permissions.PERMISSIONS.values()) {
      if (permission.isGlobal()) {
        set.add(permission);
      }
    }
    return set;
  }

  /**
   * obtains non-global permissions.
   *
   * @return non-global permission.
   */
  @NotNull
  Collection<Permission> getNonGlobalPermissions() {
    final var set = new HashSet<Permission>();
    for (final var permission : Permissions.PERMISSIONS.values()) {
      if (!permission.isGlobal()) {
        set.add(permission);
      }
    }
    return set;
  }

  /**
   * obtains permissions.
   *
   * @return permission.
   */
  @NotNull
  Collection<Permission> getPermissions() {
    return Collections.unmodifiableCollection(Permissions.PERMISSIONS.values());
  }

  /**
   * registers the permission.
   *
   * @param permission the permission to register.
   */
  void register(@NotNull final Permission permission) {
    Permissions.PERMISSIONS.put(permission.getId(), permission);
  }

  /**
   * registers the actions.
   *
   * @param action the action to register.
   */
  void register(@NotNull final Action action) {
    Permissions.ACTIONS.add(action);
  }
}
