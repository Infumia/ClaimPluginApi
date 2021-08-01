package tr.com.infumia.claimplugin.paper.api.permission;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.claimplugin.paper.api.event.LocationalEvent;

/**
 * a class that contains default permissions for Claim plugin.
 */
final class Permissions {

  /**
   * the actions.
   */
  private static final Map<ControlResult, Collection<Consumer<LocationalEvent>>> ACTIONS = new ConcurrentHashMap<>();

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
   * obtains all registered permissions.
   *
   * @return all registered permissions
   */
  @NotNull
  static Collection<Permission> all() {
    return Permissions.PERMISSIONS.values();
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
   * gets action via result.
   *
   * @param result the result to get.
   *
   * @return action.
   */
  @NotNull
  static Optional<Collection<Consumer<LocationalEvent>>> get(@NotNull final ControlResult result) {
    return Optional.ofNullable(Permissions.ACTIONS.get(result));
  }

  /**
   * obtains global permissions.
   *
   * @return global permission.
   */
  @NotNull
  static Collection<Permission> getGlobalPermissions() {
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
  static Collection<Permission> getNonGlobalPermissions() {
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
  static Collection<Permission> getPermissions() {
    return Collections.unmodifiableCollection(Permissions.PERMISSIONS.values());
  }

  /**
   * registers the permission.
   *
   * @param permission the permission to register.
   */
  static void register(@NotNull final Permission permission) {
    Permissions.PERMISSIONS.put(permission.getId(), permission);
  }

  /**
   * registers the actions.
   *
   * @param result the result to register.
   * @param action the action to register.
   */
  static void register(@NotNull final ControlResult result, @NotNull final Consumer<LocationalEvent> action) {
    final var actions = Permissions.ACTIONS.getOrDefault(result, new HashSet<>());
    actions.add(action);
    Permissions.ACTIONS.put(result, actions);
  }
}
