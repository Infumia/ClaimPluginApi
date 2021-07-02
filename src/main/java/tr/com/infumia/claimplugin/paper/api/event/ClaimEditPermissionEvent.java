package tr.com.infumia.claimplugin.paper.api.event;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Cancellable;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.claimplugin.paper.api.claim.Claim;
import tr.com.infumia.claimplugin.paper.api.permission.Permission;

/**
 * a class that represents claim edit permission events that fire when someone edits the permission.
 * <p>
 * the editor can be claim owner, claim member or server admin which has {@literal claim.admin.edit-permission}.
 */
public final class ClaimEditPermissionEvent extends ClaimEvent implements Cancellable {

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
  public ClaimEditPermissionEvent(final @NotNull Claim claim, @NotNull final CommandSender editor,
                                  @NotNull final Permission permission, @NotNull final Permission.Status status) {
    super(claim);
    this.editor = editor;
    this.permission = permission;
    this.status = status;
  }

  /**
   * ctor.
   *
   * @param isAsync the async.
   * @param claim the claim.
   * @param editor the editor.
   * @param permission the permission.
   * @param status the status.
   */
  public ClaimEditPermissionEvent(final boolean isAsync, @NotNull final Claim claim,
                                  @NotNull final CommandSender editor, @NotNull final Permission permission,
                                  @NotNull final Permission.Status status) {
    super(isAsync, claim);
    this.editor = editor;
    this.permission = permission;
    this.status = status;
  }
}