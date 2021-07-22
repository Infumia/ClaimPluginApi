package tr.com.infumia.claimplugin.paper.api.claim;

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
   * the main claim.
   */
  MAIN("main"),
  /**
   * the sub claim.
   */
  SUB("sub"),
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
    for (final var claimType : ClaimType.VALUES) {
      if (claimType.type.equalsIgnoreCase(type)) {
        return Optional.of(claimType);
      }
    }
    return Optional.empty();
  }
}
