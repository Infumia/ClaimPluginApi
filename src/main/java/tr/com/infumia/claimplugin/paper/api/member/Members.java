package tr.com.infumia.claimplugin.paper.api.member;

import java.util.Objects;
import java.util.UUID;
import lombok.Setter;
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
  @Setter
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
  static Member create(@NotNull final UUID uniqueId) {
    return Objects.requireNonNull(Members.memberCreator, "member provider").apply(uniqueId);
  }
}
