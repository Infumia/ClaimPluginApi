package tr.com.infumia.claimplugin.paper.api.permission;

import org.jetbrains.annotations.NotNull;
import tr.com.infumia.claimplugin.paper.api.event.LocationalEvent;

/**
 * a functional interface to determine controls.
 */
@FunctionalInterface
public interface Control {

  /**
   * controls all the permissions.
   *
   * @param event the event to control.
   *
   * @return {@code true} if the event passes the control.
   */
  @NotNull
  ControlResult control(@NotNull LocationalEvent event);
}
