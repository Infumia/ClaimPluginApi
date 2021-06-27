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
   * the member provider.
   */
  @Nullable
  @Setter
  private static MemberProvider memberProvider;

  /**
   * ctor.
   */
  private Members() {
  }

  /**
   * gets or creates the member via unique id.
   *
   * @param uniqueId the unique id to get.
   *
   * @return member.
   */
  @NotNull
  static Member get(@NotNull final UUID uniqueId) {
    return Objects.requireNonNull(Members.memberProvider, "member provider").apply(uniqueId);
  }
}
