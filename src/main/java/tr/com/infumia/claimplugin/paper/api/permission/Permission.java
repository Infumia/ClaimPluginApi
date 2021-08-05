package tr.com.infumia.claimplugin.paper.api.permission;

import java.util.Collection;
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
   * obtains all registered permissions.
   *
   * @return all registered permissions
   */
  @NotNull
  static Collection<Permission> all() {
    return Permissions.all();
  }

  /**
   * gets action via result.
   *
   * @return action.
   */
  @NotNull
  static Collection<Action> allActions() {
    return Permissions.allActions();
  }

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
   * obtains the anvil access permission.
   *
   * @return anvil access permission.
   *
   * @throws IllegalStateException if anvil access permission not found.
   */
  @NotNull
  static Permission anvilAccess() {
    return Permission.getOrThrow("anvil-access");
  }

  /**
   * obtains the armor stand access permission.
   *
   * @return armor stand access permission.
   *
   * @throws IllegalStateException if armor stand access permission not found.
   */
  @NotNull
  static Permission armorStandAccess() {
    return Permission.getOrThrow("armor-stand-access");
  }

  /**
   * obtains the barrel access permission.
   *
   * @return barrel access permission.
   *
   * @throws IllegalStateException if barrel access permission not found.
   */
  @NotNull
  static Permission barrelAccess() {
    return Permission.getOrThrow("barrel-access");
  }

  /**
   * obtains the block break permission.
   *
   * @return block break permission.
   *
   * @throws IllegalStateException if block break permission not found.
   */
  @NotNull
  static Permission blockBreak() {
    return Permission.getOrThrow("block-break");
  }

  /**
   * obtains the block place permission.
   *
   * @return block place permission.
   *
   * @throws IllegalStateException if block place permission not found.
   */
  @NotNull
  static Permission blockPlace() {
    return Permission.getOrThrow("block-place");
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
    return Permission.getOrThrow("protection");
  }

  /**
   * obtains the brewing access permission.
   *
   * @return brewing access permission.
   *
   * @throws IllegalStateException if brewing access permission not found.
   */
  @NotNull
  static Permission brewingAccess() {
    return Permission.getOrThrow("brewing-access");
  }

  /**
   * obtains the chest access permission.
   *
   * @return chest access permission.
   *
   * @throws IllegalStateException if chest access permission not found.
   */
  @NotNull
  static Permission chestAccess() {
    return Permission.getOrThrow("chest-access");
  }

  /**
   * obtains the dragon egg touch permission.
   *
   * @return dragon egg touch permission.
   *
   * @throws IllegalStateException if dragon egg touch permission not found.
   */
  @NotNull
  static Permission dragonEggTouch() {
    return Permission.getOrThrow("dragon-egg-touch");
  }

  /**
   * obtains the edit perm permission.
   *
   * @return edit perm permission.
   *
   * @throws IllegalStateException if edit perm permission not found.
   */
  @NotNull
  static Permission editPerm() {
    return Permission.getOrThrow("edit-perm");
  }

  /**
   * obtains the furnace access permission.
   *
   * @return furnace access permission.
   *
   * @throws IllegalStateException if furnace access permission not found.
   */
  @NotNull
  static Permission furnaceAccess() {
    return Permission.getOrThrow("furnace-access");
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
   * obtains global permissions.
   *
   * @return global permission.
   */
  @NotNull
  static Collection<Permission> getGlobalPermissions() {
    return Permissions.getGlobalPermissions();
  }

  /**
   * obtains non-global permissions.
   *
   * @return non-global permission.
   */
  @NotNull
  static Collection<Permission> getNonGlobalPermissions() {
    return Permissions.getNonGlobalPermissions();
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
   * obtains permissions.
   *
   * @return permission.
   */
  @NotNull
  static Collection<Permission> getPermissions() {
    return Permissions.getPermissions();
  }

  /**
   * obtains the home permission.
   *
   * @return home permission.
   *
   * @throws IllegalStateException if home permission not found.
   */
  @NotNull
  static Permission home() {
    return Permission.getOrThrow("home");
  }

  /**
   * obtains the invite member permission.
   *
   * @return invite member permission.
   *
   * @throws IllegalStateException if invite member permission not found.
   */
  @NotNull
  static Permission inviteMember() {
    return Permission.getOrThrow("invite-member");
  }

  /**
   * obtains the kick member permission.
   *
   * @return kick member permission.
   *
   * @throws IllegalStateException if kick member permission not found.
   */
  @NotNull
  static Permission kickMember() {
    return Permission.getOrThrow("kick-member");
  }

  /**
   * obtains the lava placement permission.
   *
   * @return lava placement permission.
   *
   * @throws IllegalStateException if lava placement permission not found.
   */
  @NotNull
  static Permission lavaPlacement() {
    return Permission.getOrThrow("lava-placement");
  }

  /**
   * obtains the member animal damage permission.
   *
   * @return member animal damage permission.
   *
   * @throws IllegalStateException if member animal damage permission not found.
   */
  @NotNull
  static Permission memberAnimalDamage() {
    return Permission.getOrThrow("member-animal-damage");
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
   * registers the actions.
   *
   * @param action the action to register.
   */
  static void register(@NotNull final Action action) {
    Permissions.register(action);
  }

  /**
   * obtains the set home permission.
   *
   * @return set home permission.
   *
   * @throws IllegalStateException if set home permission not found.
   */
  @NotNull
  static Permission setHome() {
    return Permission.getOrThrow("set-home");
  }

  /**
   * obtains the shulker access permission.
   *
   * @return shulker access permission.
   *
   * @throws IllegalStateException if shulker access permission not found.
   */
  @NotNull
  static Permission shulkerAccess() {
    return Permission.getOrThrow("shulker-access");
  }

  /**
   * obtains the storage access permission.
   *
   * @return storage access permission.
   *
   * @throws IllegalStateException if storage access permission not found.
   */
  @NotNull
  static Permission storageAccess() {
    return Permission.getOrThrow("storage-access");
  }

  /**
   * obtains the water placement permission.
   *
   * @return water placement permission.
   *
   * @throws IllegalStateException if water placement permission not found.
   */
  @NotNull
  static Permission waterPlacement() {
    return Permission.getOrThrow("water-placement");
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
   * obtains the description.
   *
   * @return description.
   */
  @NotNull
  String getDescription();

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
     * the values.
     */
    private static final Status[] VALUES = Status.values();

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
      for (final var status : Status.VALUES) {
        if (status.getId().equalsIgnoreCase(id)) {
          return status;
        }
      }
      return Status.ENABLED;
    }
  }
}
