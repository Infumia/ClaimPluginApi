package tr.com.infumia.claimplugin.paper.api.claim;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.infumialib.paper.smartinventory.util.SlotPos;

/**
 * an interface to determine storage.
 */
public interface Storage {

  /**
   * creates an empty storage instance.
   *
   * @return storage instance.
   */
  @NotNull
  static Storage empty() {
    return new Impl();
  }

  /**
   * creates an empty storage instance.
   *
   * @param items the items to create.
   *
   * @return storage instance.
   */
  @NotNull
  static Storage of(@NotNull final Map<Integer, Map<SlotPos, ItemStack>> items) {
    return new Impl(items);
  }

  /**
   * adds the item to the page at pos.
   *
   * @param page the page to add.
   * @param position the position to add.
   * @param itemStack the item stack to add.
   */
  default void addItem(final int page, @NotNull final SlotPos position, @NotNull final ItemStack itemStack) {
    this.getPage(page).ifPresent(map -> map.put(position, itemStack));
  }

  /**
   * gets items in page at position.
   *
   * @param page the page to get.
   * @param position the position to get.
   *
   * @return item in page at position.
   */
  @NotNull
  default Optional<ItemStack> getItem(final int page, @NotNull final SlotPos position) {
    return this.getPage(page).map(map -> map.get(position));
  }

  /**
   * obtains the items.
   *
   * @return items.
   */
  @NotNull
  Map<Integer, Map<SlotPos, ItemStack>> getItems();

  /**
   * gets the items at page.
   *
   * @param page the page to get.
   *
   * @return items at page.
   */
  @NotNull
  Optional<Map<SlotPos, ItemStack>> getPage(int page);

  /**
   * removes the item in page at position.
   *
   * @param page the page to remove.
   * @param position the position to remove.
   */
  default void removeItem(final int page, @NotNull final SlotPos position) {
    this.getPage(page).ifPresent(map -> map.remove(position));
  }

  /**
   * removes the page.
   *
   * @param page the page to remove.
   */
  void removePage(int page);

  /**
   * a simple implementation of {@link Storage}.
   */
  @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
  final class Impl implements Storage {

    /**
     * the items.
     */
    @NotNull
    private final Map<Integer, Map<SlotPos, ItemStack>> items;

    /**
     * ctor.
     */
    private Impl() {
      this(new HashMap<>());
    }

    @NotNull
    @Override
    public Map<Integer, Map<SlotPos, ItemStack>> getItems() {
      return Collections.unmodifiableMap(this.items);
    }

    @NotNull
    @Override
    public Optional<Map<SlotPos, ItemStack>> getPage(final int page) {
      return Optional.ofNullable(this.items.get(page));
    }

    @Override
    public void removePage(final int page) {
      this.items.remove(page);
    }
  }
}
