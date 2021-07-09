package tr.com.infumia.claimplugin.paper.api.member;

import java.util.Objects;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that contains utility methods for members.
 */
public final class Members {

  /**
   * the member creator.
   */
  @Nullable
  private static MemberCreator memberCreator;

  /**
   * ctor.
   */
  private Members() {
  }

  /**
   * creates the member via unique id.
   *
   * @param uniqueId the unique id to get.
   *
   * @return a newly created member instance.
   */
  @NotNull
  static Member createMember(@NotNull final UUID uniqueId) {
    return Members.getMemberCreator().createMember(uniqueId);
  }

  /**
   * creates the owner via unique id.
   *
   * @param uniqueId the unique id to get.
   *
   * @return a newly created member instance.
   */
  @NotNull
  static Member createOwner(@NotNull final UUID uniqueId) {
    return Members.getMemberCreator().createOwner(uniqueId);
  }

  /**
   * obtains the member creator.
   *
   * @return member creator.
   */
  @NotNull
  private static MemberCreator getMemberCreator() {
    return Objects.requireNonNull(Members.memberCreator, "member provider");
  }

  /**
   * sets the member creator if it's not set already.
   *
   * @param memberCreator the member creator to set.
   */
  public static void setMemberCreator(@NotNull final MemberCreator memberCreator) {
    if (Members.memberCreator != null) {
      Members.memberCreator = memberCreator;
    }
  }
}
