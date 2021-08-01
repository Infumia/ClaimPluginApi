package tr.com.infumia.claimplugin.paper.api.permission;

import java.util.function.Consumer;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.claimplugin.paper.api.event.LocationalEvent;

/**
 * an interface to determine actions for each {@link ControlResult}.
 */
public interface Action extends Consumer<@NotNull LocationalEvent> {

  /**
   * registers the action.
   */
  default void register() {
    Permission.register(this);
  }
}
