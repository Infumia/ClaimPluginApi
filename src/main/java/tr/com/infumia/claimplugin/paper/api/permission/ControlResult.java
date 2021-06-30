package tr.com.infumia.claimplugin.paper.api.permission;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

/**
 * an enum that contains control results.
 */
@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public final class ControlResult {

  /**
   * returns when someone attacks animals where are in the claim.
   */
  public static final ControlResult ANIMAL_DAMAGE = new ControlResult("animal-damage");

  /**
   * returns when someone interacts with the anvil.
   */
  public static final ControlResult ANVIL_ACCESS = new ControlResult("anvil-access");

  /**
   * returns when someone interacts with the armor stand.
   */
  public static final ControlResult ARMOR_STAND_ACCESS = new ControlResult("armor-stand-acces");

  /**
   * returns when someone interacts with the barrel.
   */
  public static final ControlResult BARREL_ACCESS = new ControlResult("barrel-access");

  /**
   * returns when someone breaks a block.
   */
  public static final ControlResult BLOCK_BREAK = new ControlResult("block-break");

  /**
   * returns when someone places a block.
   */
  public static final ControlResult BLOCK_PLACE = new ControlResult("block-place");

  /**
   * returns when someone interacts with the brewing.
   */
  public static final ControlResult BREWING_ACCESS = new ControlResult("brewing-access");

  /**
   * returns when someone interacts with the chest.
   */
  public static final ControlResult CHEST_ACCESS = new ControlResult("chest-access");

  /**
   * returns when someone touches to the dragon egg.
   */
  public static final ControlResult DRAGON_EGG_TOUCH = new ControlResult("dragon-egg-touch");

  /**
   * returns when an entity has made a decision to explode or a block explodes.
   */
  public static final ControlResult EXPLOSIONS = new ControlResult("explosions");

  /**
   * returns when fire spreads to a block.
   */
  public static final ControlResult FIRE_SPREAD = new ControlResult("fire-spread");

  /**
   * returns when someone interacts with the furnace.
   */
  public static final ControlResult FURNACE_ACCESS = new ControlResult("furnace-access");

  /**
   * returns when claim's global permissions are empty.
   */
  public static final ControlResult GLOBAL_EMPTY_PERMISSION = new ControlResult("global-empty-permission");

  /**
   * returns when the control isn't related to the event.
   */
  public static final ControlResult INVALID = new ControlResult("invalid", true);

  /**
   * returns when someone places lava.
   */
  public static final ControlResult LAVA_PLACEMENT = new ControlResult("lava-placement");

  /**
   * returns when member's permissions are empty.
   */
  public static final ControlResult MEMBER_EMPTY_PERMISSION = new ControlResult("member-empty-permission");

  /**
   * returns when an entity spawns or an entity is spawned by a spawner in the claim.
   */
  public static final ControlResult MOB_SPAWNING = new ControlResult("mob-spawning");

  /**
   * returns when the actor is not null and no match member for the actor.
   * <p>
   * it means a non-member player interact to the claim.
   */
  public static final ControlResult NONE = new ControlResult("none");

  /**
   * returns when a non-member player interacts with the claim.
   */
  public static final ControlResult NOT_MEMBER = new ControlResult("not-member");

  /**
   * returns when any events happen in a protection claim.
   */
  public static final ControlResult PROTECTION_CLAIM = new ControlResult("protection-claim");

  /**
   * returns when players do a fight with any player in the claim which forbids pvp.
   */
  public static final ControlResult PVP = new ControlResult("pvp");

  /**
   * returns when someone interacts with shulkers.
   */
  public static final ControlResult SHULKER_ACCESS = new ControlResult("shulker-access");

  /**
   * returns when the actor interact to the claim successfully.
   */
  public static final ControlResult SUCCEED = new ControlResult("succeed", true);

  /**
   * returns when someone places water.
   */
  public static final ControlResult WATER_PLACEMENT = new ControlResult("water-placement");

  /**
   * the id.
   */
  @NotNull
  private final String id;

  /**
   * the succeed.
   */
  private final boolean succeed;

  /**
   * ctor.
   *
   * @param id the id.
   */
  public ControlResult(@NotNull final String id) {
    this(id, false);
  }
}
