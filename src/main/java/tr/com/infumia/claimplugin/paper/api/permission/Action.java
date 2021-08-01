package tr.com.infumia.claimplugin.paper.api.permission;

import java.util.function.BiConsumer;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.claimplugin.paper.api.event.LocationalEvent;

/**
 * an interface to determine actions for each {@link ControlResult}.
 */
public interface Action extends BiConsumer<@NotNull ControlResult, @NotNull LocationalEvent> {

  /**
   * registers the action.
   */
  default void register() {
    Permission.register(this);
  }
}
