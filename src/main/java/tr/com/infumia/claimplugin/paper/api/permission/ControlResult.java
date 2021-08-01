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
  public static final ControlResult ANIMAL_DAMAGE = ControlResult.failed("animal-damage");

  /**
   * returns when someone interacts with the anvil.
   */
  public static final ControlResult ANVIL_ACCESS = ControlResult.failed("anvil-access");

  /**
   * returns when someone interacts with the armor stand.
   */
  public static final ControlResult ARMOR_STAND_ACCESS = ControlResult.failed("armor-stand-access");

  /**
   * returns when someone interacts with the barrel.
   */
  public static final ControlResult BARREL_ACCESS = ControlResult.failed("barrel-access");

  /**
   * returns when someone interacts with the beacon.
   */
  public static final ControlResult BEACON_ACCESS = ControlResult.failed("beacon-access");

  /**
   * returns when someone interacts with the beacon's effect.
   */
  public static final ControlResult BEACON_EFFECT_ACCESS = ControlResult.failed("beacon-effect-access");

  /**
   * returns when someone breaks a block.
   */
  public static final ControlResult BLOCK_BREAK = ControlResult.failed("block-break");

  /**
   * returns when someone places a block.
   */
  public static final ControlResult BLOCK_PLACE = ControlResult.failed("block-place");

  /**
   * returns when someone interacts with the brewing.
   */
  public static final ControlResult BREWING_ACCESS = ControlResult.failed("brewing-access");

  /**
   * returns when someone interacts with the chest.
   */
  public static final ControlResult CHEST_ACCESS = ControlResult.failed("chest-access");

  /**
   * returns when someone wants to delete the home.
   */
  public static final ControlResult DELETE_HOME = ControlResult.failed("delete-home");

  /**
   * returns when someone touches to the dragon egg.
   */
  public static final ControlResult DRAGON_EGG_TOUCH = ControlResult.failed("dragon-egg-touch");

  /**
   * returns when someone edits the permission of a claim.
   */
  public static final ControlResult EDIT_PERM = ControlResult.failed("edit-perm");

  /**
   * returns when someone interacts with the enchanting table.
   */
  public static final ControlResult ENCHANTING_TABLE_ACCESS = ControlResult.failed("enchanting-table-access");

  /**
   * returns when someone interacts with the furnace.
   */
  public static final ControlResult FURNACE_ACCESS = ControlResult.failed("furnace-access");

  /**
   * returns when someone wants to teleport to claim via home.
   */
  public static final ControlResult HOME = ControlResult.failed("home");

  /**
   * returns when the control isn't related to the event.
   */
  public static final ControlResult INVALID = ControlResult.succeed("invalid");

  /**
   * returns when claim owners or members tries to invite a player to their claims.
   */
  public static final ControlResult INVITE_MEMBER = ControlResult.failed("invite-member");

  /**
   * returns when someone invites to claim itself.
   */
  public static final ControlResult INVITE_YOURSELF = ControlResult.failed("invite-yourself");

  /**
   * returns when someone interacts with the item frame.
   */
  public static final ControlResult ITEM_FRAME_ACCESS = ControlResult.failed("item-frame-access");

  /**
   * returns when someone kicks the member from the claim.
   */
  public static final ControlResult KICK_MEMBER = ControlResult.failed("kick-member");

  /**
   * returns when someone places lava.
   */
  public static final ControlResult LAVA_PLACEMENT = ControlResult.failed("lava-placement");

  /**
   * returns when an entity spawns or an entity is spawned by a spawner in the claim.
   */
  public static final ControlResult MOB_SPAWNING = ControlResult.failed("mob-spawning");

  /**
   * returns when the actor is not null and no match member for the actor.
   * <p>
   * it means a non-member player interact to the claim.
   */
  public static final ControlResult NONE = ControlResult.failed("none");

  /**
   * returns when a non-member player interacts with the claim.
   */
  public static final ControlResult NOT_MEMBER = ControlResult.failed("not-member");

  /**
   * returns when the permissions check the owner of the claim.
   */
  public static final ControlResult OWNER = ControlResult.succeed("owner");

  /**
   * usually uses when a global permission needs more detail to calculate it with member permissions.
   */
  public static final ControlResult PASS = ControlResult.succeed("pass");

  /**
   * returns when someone or something interacts with a thing which is in a claim.
   */
  public static final ControlResult PROTECTION = ControlResult.failed("protection");

  /**
   * returns when any events happen in a protection claim.
   */
  public static final ControlResult PROTECTION_CLAIM = ControlResult.failed("protection-claim");

  /**
   * returns when players do a fight with any player in the claim which forbids pvp.
   */
  public static final ControlResult PVP = ControlResult.failed("pvp");

  /**
   * returns when someone tries to set home.
   */
  public static final ControlResult SET_HOME = ControlResult.failed("set-home");

  /**
   * returns when someone tries to set home but it reached the home limit.
   */
  public static final ControlResult SET_HOME_LIMIT = ControlResult.failed("set-home-limit");

  /**
   * returns when someone tries to set home and it's succeed
   */
  public static final ControlResult SET_HOME_SUCCEED = ControlResult.succeed("set-home-succeed");

  /**
   * returns when someone interacts with shulkers.
   */
  public static final ControlResult SHULKER_ACCESS = ControlResult.failed("shulker-access");

  /**
   * returns when someone wants to access to storage of the claim.
   */
  public static final ControlResult STORAGE_ACCESS = ControlResult.failed("storage-access");

  /**
   * returns when the actor interact to the claim successfully.
   */
  public static final ControlResult SUCCEED = ControlResult.succeed("succeed");

  /**
   * returns when someone places water.
   */
  public static final ControlResult WATER_PLACEMENT = ControlResult.failed("water-placement");

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
   * creates a succeed control result.
   *
   * @param id the id to create.
   *
   * @return a newly created succeed control result.
   */
  @NotNull
  public static ControlResult failed(@NotNull final String id) {
    return new ControlResult(id, false);
  }

  /**
   * creates an instance of control result.
   *
   * @param id the id to create.
   *
   * @return a newly created control result.
   */
  @NotNull
  public static ControlResult succeed(@NotNull final String id) {
    return new ControlResult(id, true);
  }
}
