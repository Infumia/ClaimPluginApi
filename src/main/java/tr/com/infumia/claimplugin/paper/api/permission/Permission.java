package tr.com.infumia.claimplugin.paper.api.permission;

import java.util.Arrays;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.claimplugin.paper.api.event.LocationalEvent;

/**
 * an interface to determine permissions.
 */
public interface Permission {

  /**
   * obtains the animal damage permission.
   *
   * @return animal damage permission.
   *
   * @throws IllegalStateException if animal damage permission not found.
   */
  @NotNull
  static Permission animalDamage() {
    return Permission.getOrThrow("animal-damage");
  }

  /**
   * obtains the block protection permission.
   *
   * @return block protection permission.
   *
   * @throws IllegalStateException if block protection permission not found.
   */
  @NotNull
  static Permission blockProtection() {
    return Permission.getOrThrow("block-protection");
  }

  /**
   * gets permission via id.
   *
   * @param id the id to get.
   *
   * @return permission.
   */
  @NotNull
  static Optional<Permission> get(@NotNull final String id) {
    return Permissions.get(id);
  }

  /**
   * gets permission via id.
   *
   * @param id the id to get.
   *
   * @return permission.
   *
   * @throws IllegalStateException if the permission not found.
   */
  @NotNull
  static Permission getOrThrow(@NotNull final String id) {
    return Permission.get(id).orElseThrow(() ->
      new IllegalStateException(String.format("Permission called %s not found!", id)));
  }

  /**
   * obtains the mob spawning permission.
   *
   * @return mob spawning permission.
   *
   * @throws IllegalStateException if mob spawning permission not found.
   */
  @NotNull
  static Permission mobSpawning() {
    return Permission.getOrThrow("mob-spawning");
  }

  /**
   * obtains the pvp permission.
   *
   * @return pvp permission.
   *
   * @throws IllegalStateException if pvp permission not found.
   */
  @NotNull
  static Permission pvp() {
    return Permission.getOrThrow("pvp");
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
   *
   * @return {@code true} if the event passes the control.
   */
  @NotNull
  ControlResult control(@NotNull LocationalEvent event);

  /**
   * obtains the id.
   *
   * @return id.
   */
  @NotNull
  String getId();

  /**
   * obtains the name.
   *
   * @return name.
   */
  @NotNull
  String getName();

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
