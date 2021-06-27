package tr.com.infumia.claimplugin.paper.api.permission;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * an enum that contains control results.
 */
@Getter
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public enum ControlResult {
  /**
   * returns when the actor doing a fight with any player in a claim which forbids pvp.
   */
  PVP_OFF,
  /**
   * returns when the actor is null and global permissions of the claim are empty or member's permissions are empty.
   */
  EMPTY_PERMISSION,
  /**
   * returns when the actor is not null and no match member for the actor.
   * <p>
   * it means a non-member player interact to the claim.
   */
  NONE,
  /**
   * returns when the actor interact to the claim successfully.
   */
  SUCCEED(true);

  /**
   * the succeed.
   */
  private final boolean succeed;

  /**
   * ctor.
   */
  ControlResult() {
    this(false);
  }

  /**
   * checks the status can pass the control.
   *
   * @param status the status to check.
   *
   * @return {@code true} if the status can pass the control.
   */
  public boolean check(@NotNull final Permission.Status status) {
    return this.isSucceed() && status == Permission.Status.ENABLED;
  }
}
