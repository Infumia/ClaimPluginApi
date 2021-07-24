package tr.com.infumia.claimplugin.paper.api.permission;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

/**
 * an enum that contains control results.
 */
@Getter
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class ControlResult {

  /**
   * returns when someone attacks animals where are in the claim.
   */
  public static final ControlResult ANIMAL_DAMAGE = ControlResult.of("animal-damage");

  /**
   * returns when someone interacts with the anvil.
   */
  public static final ControlResult ANVIL_ACCESS = ControlResult.of("anvil-access");

  /**
   * returns when someone interacts with the armor stand.
   */
  public static final ControlResult ARMOR_STAND_ACCESS = ControlResult.of("armor-stand-access");

  /**
   * returns when someone interacts with the barrel.
   */
  public static final ControlResult BARREL_ACCESS = ControlResult.of("barrel-access");

  /**
   * returns when someone breaks a block.
   */
  public static final ControlResult BLOCK_BREAK = ControlResult.of("block-break");

  /**
   * returns when someone places a block.
   */
  public static final ControlResult BLOCK_PLACE = ControlResult.of("block-place");

  /**
   * returns when someone or something access to a block where is in a claim..
   */
  public static final ControlResult BLOCK_PROTECTION = ControlResult.of("block-protection");

  /**
   * returns when someone interacts with the brewing.
   */
  public static final ControlResult BREWING_ACCESS = ControlResult.of("brewing-access");

  /**
   * returns when someone interacts with the chest.
   */
  public static final ControlResult CHEST_ACCESS = ControlResult.of("chest-access");

  /**
   * returns when someone touches to the dragon egg.
   */
  public static final ControlResult DRAGON_EGG_TOUCH = ControlResult.of("dragon-egg-touch");

  /**
   * returns when someone edits the permission of a claim.
   */
  public static final ControlResult EDIT_PERM = ControlResult.of("edit-perm");

  /**
   * returns when someone interacts with the furnace.
   */
  public static final ControlResult FURNACE_ACCESS = ControlResult.of("furnace-access");

  /**
   * returns when someone wants to teleport to claim via home.
   */
  public static final ControlResult HOME = ControlResult.of("home");

  /**
   * returns when the control isn't related to the event.
   */
  public static final ControlResult INVALID = ControlResult.of("invalid", true);

  /**
   * returns when claim owners or members tries to invite a player to their claims.
   */
  public static final ControlResult INVITE_MEMBER = ControlResult.of("invite-member");

  /**
   * returns when someone invites to claim itself.
   */
  public static final ControlResult INVITE_YOURSELF = ControlResult.of("invite-yourself");

  /**
   * returns when someone kicks the member from the claim.
   */
  public static final ControlResult KICK_MEMBER = ControlResult.of("kick-member");

  /**
   * returns when someone places lava.
   */
  public static final ControlResult LAVA_PLACEMENT = ControlResult.of("lava-placement");

  /**
   * returns when an entity spawns or an entity is spawned by a spawner in the claim.
   */
  public static final ControlResult MOB_SPAWNING = ControlResult.of("mob-spawning");

  /**
   * returns when the actor is not null and no match member for the actor.
   * <p>
   * it means a non-member player interact to the claim.
   */
  public static final ControlResult NONE = ControlResult.of("none");

  /**
   * returns when a non-member player interacts with the claim.
   */
  public static final ControlResult NOT_MEMBER = ControlResult.of("not-member");

  /**
   * returns when the permissions check the owner of the claim.
   */
  public static final ControlResult OWNER = ControlResult.of("owner", true);

  /**
   * usually uses when a global permission needs more detail to calculate it with member permissions.
   */
  public static final ControlResult PASS = ControlResult.of("pass", true);

  /**
   * returns when any events happen in a protection claim.
   */
  public static final ControlResult PROTECTION_CLAIM = ControlResult.of("protection-claim");

  /**
   * returns when players do a fight with any player in the claim which forbids pvp.
   */
  public static final ControlResult PVP = ControlResult.of("pvp");

  /**
   * returns when someone tries to set home.
   */
  public static final ControlResult SET_HOME = ControlResult.of("set-home");

  /**
   * returns when someone tries to set home but it reached the home limit.
   */
  public static final ControlResult SET_HOME_LIMIT = ControlResult.of("set-home-limit");

  /**
   * returns when someone tries to set home and it's succeed
   */
  public static final ControlResult SET_HOME_SUCCEED = ControlResult.of("set-home-succeed", true);

  /**
   * returns when someone interacts with shulkers.
   */
  public static final ControlResult SHULKER_ACCESS = ControlResult.of("shulker-access");

  /**
   * returns when someone wants to access to storage of the claim.
   */
  public static final ControlResult STORAGE_ACCESS = ControlResult.of("storage-access");

  /**
   * returns when the actor interact to the claim successfully.
   */
  public static final ControlResult SUCCEED = ControlResult.of("succeed", true);

  /**
   * returns when someone places water.
   */
  public static final ControlResult WATER_PLACEMENT = ControlResult.of("water-placement");

  /**
   * the id.
   */
  @NotNull
  @ToString.Include
  @EqualsAndHashCode.Include
  private final String id;

  /**
   * the succeed.
   */
  @ToString.Include
  @EqualsAndHashCode.Include
  private final boolean succeed;

  /**
   * creates an instance of control result.
   *
   * @param id the id to create.
   * @param succeed the succeed to create.
   *
   * @return a newly created control result.
   */
  @NotNull
  public static ControlResult of(@NotNull final String id, final boolean succeed) {
    return new ControlResult(id, succeed);
  }

  /**
   * creates a succeed control result.
   *
   * @param id the id to create.
   *
   * @return a newly created succeed control result.
   */
  @NotNull
  public static ControlResult of(@NotNull final String id) {
    return ControlResult.of(id, false);
  }
}
