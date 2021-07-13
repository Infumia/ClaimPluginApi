package tr.com.infumia.claimplugin.paper.api.storage;

import java.util.Map;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tr.com.infumia.infumialib.paper.smartinventory.util.SlotPos;

/**
 * an interface to determine storage creators.
 */
public interface StorageCreator {

  /**
   * creates an empty storage instance.
   *
   * @param player the player to create. this is for getting default slot count of the player.
   *
   * @return empty storage instance.
   */
  @NotNull
  Storage empty(@Nullable Player player);

  /**
   * creates a new storage instance.
   *
   * @param slotSize the slot size to create.
   * @param items the items to create.
   *
   * @return a newly created storage instance.
   */
  @NotNull
  Storage of(final int slotSize, @NotNull final Map<Integer, Map<SlotPos, ItemStack>> items);
}
