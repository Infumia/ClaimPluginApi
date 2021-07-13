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
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
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
  public static final ControlResult ARMOR_STAND_ACCESS = new ControlResult("armor-stand-access");

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
   * returns when someone or something access to a block where is in a claim..
   */
  public static final ControlResult BLOCK_PROTECTION = new ControlResult("block-protection");

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
   * returns when someone edits the permission of a claim.
   */
  public static final ControlResult EDIT_PERM = new ControlResult("edit-perm");

  /**
   * returns when someone interacts with the furnace.
   */
  public static final ControlResult FURNACE_ACCESS = new ControlResult("furnace-access");

  /**
   * returns when someone wants to teleport to claim via home.
   */
  public static final ControlResult HOME = new ControlResult("home");

  /**
   * returns when the control isn't related to the event.
   */
  public static final ControlResult INVALID = new ControlResult("invalid", true);

  /**
   * returns when claim owners or members tries to invite a player to their claims.
   */
  public static final ControlResult INVITE_MEMBER = new ControlResult("invite-member");

  /**
   * returns when someone places lava.
   */
  public static final ControlResult LAVA_PLACEMENT = new ControlResult("lava-placement");

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
   * returns when someone tries to set home.
   */
  public static final ControlResult SET_HOME = new ControlResult("set-home");

  /**
   * returns when someone interacts with shulkers.
   */
  public static final ControlResult SHULKER_ACCESS = new ControlResult("shulker-access");

  /**
   * returns when someone wants to access to storage of the claim.
   */
  public static final ControlResult STORAGE_ACCESS = new ControlResult("storage-access");

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
   * ctor.
   *
   * @param id the id.
   */
  public ControlResult(@NotNull final String id) {
    this(id, false);
  }
}
