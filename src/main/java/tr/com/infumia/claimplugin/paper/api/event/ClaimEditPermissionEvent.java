package tr.com.infumia.claimplugin.paper.api.event;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.claimplugin.paper.api.claim.ParentClaim;
import tr.com.infumia.claimplugin.paper.api.permission.Permission;

/**
 * a class that represents claim edit permission events that fire when someone edits the permission.
 * <p>
 * the editor can be claim owner, claim member or server admin which has {@literal claim.admin.edit-permission}.
 */
public final class ClaimEditPermissionEvent extends ClaimEvent implements Cancellable {

  /**
   * the handler list.
   */
  private static final HandlerList handlerList = new HandlerList();

  /**
   * the editor.
   */
  @NotNull
  @Getter
  private final CommandSender editor;

  /**
   * the cancelled.
   */
  @Getter
  @Setter
  private boolean cancelled;

  /**
   * the permission.
   */
  @NotNull
  @Getter
  @Setter
  private Permission permission;

  /**
   * the status.
   */
  @NotNull
  @Getter
  @Setter
  private Permission.Status status;

  /**
   * ctor.
   *
   * @param claim the claim.
   * @param editor the editor.
   * @param permission the permission.
   * @param status the status.
   */
  public ClaimEditPermissionEvent(@NotNull final ParentClaim claim, @NotNull final CommandSender editor,
                                  @NotNull final Permission permission, @NotNull final Permission.Status status) {
    super(claim);
    this.editor = editor;
    this.permission = permission;
    this.status = status;
  }

  /**
   * the handler list.
   *
   * @return handler list.
   */
  @NotNull
  public static HandlerList getHandlerList() {
    return ClaimEditPermissionEvent.handlerList;
  }

  @NotNull
  @Override
  public final HandlerList getHandlers() {
    return ClaimEditPermissionEvent.handlerList;
  }
}
