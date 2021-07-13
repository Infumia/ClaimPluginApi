package tr.com.infumia.claimplugin.paper.api.storage;

import java.util.Map;
import java.util.Objects;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tr.com.infumia.infumialib.paper.smartinventory.util.SlotPos;

/**
 * a class that contains utility methods for {@link Storage}.
 */
public final class Storages {

  /**
   * the storage creator.
   */
  @Nullable
  private static StorageCreator storageCreator;

  /**
   * ctor.
   */
  private Storages() {
  }

  /**
   * creates an empty storage instance.
   *
   * @param player the player to create. this is for getting default slot count of the player.
   *
   * @return storage instance.
   */
  @NotNull
  static Storage empty(@Nullable final Player player) {
    return Storages.getStorageCreator().empty(player);
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
  static Storage of(final int slotSize, @NotNull final Map<Integer, Map<SlotPos, ItemStack>> items) {
    return Storages.getStorageCreator().of(slotSize, items);
  }

  /**
   * gets the storage creator.
   *
   * @return storage creator.
   *
   * @throws NullPointerException if {@link #storageCreator} is {@code null}.
   */
  @NotNull
  private static StorageCreator getStorageCreator() {
    return Objects.requireNonNull(Storages.storageCreator, "storage creator");
  }

  /**
   * sets the storage creator if it's not set already.
   *
   * @param storageCreator the storage creator to set.
   */
  public static void setStorageCreator(@NotNull final StorageCreator storageCreator) {
    if (Storages.storageCreator == null) {
      Storages.storageCreator = storageCreator;
    }
  }
}
