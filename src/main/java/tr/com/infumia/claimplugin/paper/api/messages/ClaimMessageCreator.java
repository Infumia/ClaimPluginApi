package tr.com.infumia.claimplugin.paper.api.messages;

import org.jetbrains.annotations.NotNull;
import tr.com.infumia.infumialib.paper.transformer.serializers.SentTitle;

/**
 * an interface to determine claim message creator.
 */
public interface ClaimMessageCreator {

  /**
   * creates an empty claim message instance.
   *
   * @return a newly created claim message instance.
   */
  @NotNull
  ClaimMessage empty();

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
  ClaimMessage of(@NotNull String enterMessage, boolean enterQuitMessageEnabled, boolean enterQuitTitleEnabled,
                  @NotNull SentTitle enterTitle, @NotNull String quitMessage, @NotNull SentTitle quitTitle);
}
