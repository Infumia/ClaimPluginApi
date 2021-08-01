package tr.com.infumia.claimplugin.paper.api.message;

import org.jetbrains.annotations.NotNull;
import tr.com.infumia.infumialib.paper.transformer.serializers.SentTitle;
import tr.com.infumia.infumialib.replaceable.RpString;

/**
 * an interface to determine claim messages.
 */
public interface ClaimMessage {

  /**
   * a default claim message to parse values.
   */
  ClaimMessage EMPTY = ClaimMessage.empty();

  /**
   * creates an empty claim message instance.
   *
   * @return a newly created claim message instance.
   */
  @NotNull
  static ClaimMessage empty() {
    return ClaimMessages.empty();
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
  static ClaimMessage of(@NotNull final RpString enterMessage, final boolean enterQuitMessageEnabled,
                         final boolean enterQuitTitleEnabled, @NotNull final SentTitle enterTitle,
                         @NotNull final RpString quitMessage, @NotNull final SentTitle quitTitle) {
    return ClaimMessages.of(enterMessage, enterQuitMessageEnabled, enterQuitTitleEnabled, enterTitle, quitMessage,
      quitTitle);
  }

  /**
   * obtains the enter message.
   *
   * @return enter message.
   */
  @NotNull
  RpString getEnterMessage();

  /**
   * sets enter message.
   *
   * @param message the message to set.
   */
  void setEnterMessage(@NotNull RpString message);

  /**
   * obtains the enter title.
   *
   * @return enter title.
   */
  @NotNull
  SentTitle getEnterTitle();

  /**
   * sets enter title.
   *
   * @param title the title to set.
   */
  void setEnterTitle(@NotNull SentTitle title);

  /**
   * obtains the quit message.
   *
   * @return quit message.
   */
  @NotNull
  RpString getQuitMessage();

  /**
   * sets quit message.
   *
   * @param message the message to set.
   */
  void setQuitMessage(@NotNull RpString message);

  /**
   * obtains the quit title.
   *
   * @return quit title.
   */
  @NotNull
  SentTitle getQuitTitle();

  /**
   * sets quit title.
   *
   * @param title the title to set.
   */
  void setQuitTitle(@NotNull SentTitle title);

  /**
   * obtains enter quit message enabled.
   *
   * @return enter quit message enabled.
   */
  boolean isEnterQuitMessageEnabled();

  /**
   * sets enter quit message enabled.
   *
   * @param enterQuitMessageEnabled the enter quit title message to set.
   */
  void setEnterQuitMessageEnabled(boolean enterQuitMessageEnabled);

  /**
   * obtains enter quit title enabled.
   *
   * @return enter quit title enabled.
   */
  boolean isEnterQuitTitleEnabled();

  /**
   * sets enter quit title enabled.
   *
   * @param enterQuitTitleEnabled the enter quit title enabled to set.
   */
  void setEnterQuitTitleEnabled(boolean enterQuitTitleEnabled);
}
