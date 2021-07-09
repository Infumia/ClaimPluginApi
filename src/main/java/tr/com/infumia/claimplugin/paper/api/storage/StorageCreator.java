package tr.com.infumia.claimplugin.paper.api.storage;

import java.util.Map;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.infumialib.paper.smartinventory.util.SlotPos;

/**
 * an interface to determine storage creators.
 */
public interface StorageCreator {

  /**
   * creates an empty storage instance.
   *
   * @return empty storage instance.
   */
  @NotNull
  Storage empty();

  /**
   * creates a new storage instance.
   *
   * @return a newly created storage instance.
   */
  @NotNull
  Storage of(final int slotSize, @NotNull final Map<Integer, Map<SlotPos, ItemStack>> items);
}
