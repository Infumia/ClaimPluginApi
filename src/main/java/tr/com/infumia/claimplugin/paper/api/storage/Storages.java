package tr.com.infumia.claimplugin.paper.api.storage;

import java.util.Map;
import java.util.Objects;
import lombok.experimental.UtilityClass;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that contains utility methods for {@link Storage}.
 */
@UtilityClass
public class Storages {

  /**
   * the storage creator.
   */
  @Nullable
  private StorageCreator storageCreator;

  /**
   * creates an empty storage instance.
   *
   * @param player the player to create. this is for getting default slot count of the player.
   *
   * @return storage instance.
   */
  @NotNull
  Storage empty(@Nullable final Player player) {
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
  Storage of(final int slotSize, @NotNull final Map<Integer, ItemStack> items) {
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
  private StorageCreator getStorageCreator() {
    return Objects.requireNonNull(Storages.storageCreator, "storage creator");
  }

  /**
   * sets the storage creator if it's not set already.
   *
   * @param storageCreator the storage creator to set.
   */
  public void setStorageCreator(@NotNull final StorageCreator storageCreator) {
    if (Storages.storageCreator == null) {
      Storages.storageCreator = storageCreator;
    }
  }
}
