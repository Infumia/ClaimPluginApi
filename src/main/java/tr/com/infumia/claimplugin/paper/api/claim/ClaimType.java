package tr.com.infumia.claimplugin.paper.api.claim;

import java.util.Arrays;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * an enum that contains claim types.
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ClaimType {
  /**
   * the main chunk.
   */
  MAIN_CHUNK("main-chunk"),
  /**
   * the sub chunk.
   */
  SUB_CHUNK("sub-chunk"),
  /**
   * the protection.
   */
  PROTECTION("protection");

  /**
   * the values.
   */
  private static final ClaimType[] VALUES = ClaimType.values();

  /**
   * the type.
   */
  @NotNull
  @Getter
  private final String type;

  /**
   * gets the claim type.
   *
   * @param type the type to get.
   *
   * @return claim type.
   */
  @NotNull
  public static Optional<ClaimType> get(@NotNull final String type) {
    return Arrays.stream(ClaimType.VALUES)
      .filter(claimType -> claimType.type.equalsIgnoreCase(type))
      .findFirst();
  }
}
