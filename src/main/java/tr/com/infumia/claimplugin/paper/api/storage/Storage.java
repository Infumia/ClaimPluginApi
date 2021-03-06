package tr.com.infumia.claimplugin.paper.api.storage;

import java.util.Map;
import java.util.Optional;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * an interface to determine storage.
 */
public interface Storage {

  /**
   * creates an empty storage instance.
   *
   * @param player the player to create. this is for getting default slot count of the player.
   *
   * @return storage instance.
   */
  @NotNull
  static Storage empty(@Nullable final Player player) {
    return Storages.empty(player);
  }

  /**
   * creates an empty storage instance.
   *
   * @param slotSize the slot size to create.
   * @param items the items to create.
   *
   * @return storage instance.
   */
  @NotNull
  static Storage of(final int slotSize, @NotNull final Map<Integer, ItemStack> items) {
    return Storages.of(slotSize, items);
  }

  /**
   * adds the item to the position at index.
   *
   * @param index the index to add.
   * @param itemStack the item stack to add.
   */
  void addItem(final int index, @NotNull final ItemStack itemStack);

  /**
   * gets items in position at index.
   *
   * @param index the index to get.
   *
   * @return item in index at position.
   */
  @NotNull
  Optional<ItemStack> getItem(final int index);

  /**
   * obtains the items.
   *
   * @return items.
   */
  @NotNull
  Map<Integer, ItemStack> getItems();

  /**
   * gets the items at the page.
   *
   * @param page the page to get.
   * @param itemsPerPage the items pe page to get.
   *
   * @return items at page.
   */
  @NotNull
  default ItemStack[] getPage(final int page, final int itemsPerPage) {
    final var items = new ItemStack[itemsPerPage];
    final var lastIndex = (page + 1) * itemsPerPage;
    final var firstIndex = lastIndex - itemsPerPage;
    for (var value = firstIndex; value < lastIndex; value++) {
      if (value < this.getSlotSize()) {
        items[value % itemsPerPage] = this.getItem(value).orElse(new ItemStack(Material.AIR));
      }
    }
    return items;
  }

  /**
   * obtains the slot size.
   *
   * @return slot size.
   */
  int getSlotSize();

  /**
   * sets the slot size.
   *
   * @param slotSize the slot size.
   */
  void setSlotSize(int slotSize);

  /**
   * removes the item in page at index.
   *
   * @param index the index to remove.
   */
  void removeItem(final int index);
}
