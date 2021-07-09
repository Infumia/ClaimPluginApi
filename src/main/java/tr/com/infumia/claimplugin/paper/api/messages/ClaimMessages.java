package tr.com.infumia.claimplugin.paper.api.messages;

import java.util.Objects;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.infumialib.paper.transformer.serializers.SentTitle;

/**
 * a class that contains utility methods for {@link ClaimMessage}.
 */
public final class ClaimMessages {

  /**
   * claim message creator.
   */
  @NotNull
  @Setter
  private static ClaimMessageCreator claimMessageCreator;

  /**
   * creates an empty claim message instance.
   *
   * @return a newly created claim message instance.
   */
  @NotNull
  static ClaimMessage empty() {
    return ClaimMessages.getClaimMessageCreator().empty();
  }

  /**
   * creates an empty claim message instance.
   *
   * @param enterMessage the enter message to create.
   * @param enterQuitMessageEnabled enter quit message enabled to create.
   * @param enterQuitTitleEnabled enter quit title enabled to create.
   * @param enterTitle enter title to create.
   * @param quitMessage quit message to create.
   * @param quitTitle quit title to create.
   *
   * @return a newly created claim message instance.
   */
  @NotNull
  static ClaimMessage of(@NotNull final String enterMessage, final boolean enterQuitMessageEnabled,
                         final boolean enterQuitTitleEnabled, @NotNull final SentTitle enterTitle,
                         @NotNull final String quitMessage, @NotNull final SentTitle quitTitle) {
    return ClaimMessages.getClaimMessageCreator().of(enterMessage, enterQuitMessageEnabled, enterQuitTitleEnabled,
      enterTitle, quitMessage, quitTitle);
  }

  /**
   * gets the claim message creator.
   *
   * @return claim message creator.
   *
   * @throws NullPointerException if {@link #claimMessageCreator} is {@code null}.
   */
  @NotNull
  private static ClaimMessageCreator getClaimMessageCreator() {
    return Objects.requireNonNull(ClaimMessages.claimMessageCreator, "claim message creator");
  }
}
