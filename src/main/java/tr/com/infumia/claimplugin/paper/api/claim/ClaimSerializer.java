package tr.com.infumia.claimplugin.paper.api.claim;

import java.util.Collection;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * an interface to determine claim serializers.
 */
public interface ClaimSerializer {

  /**
   * gets all claims.
   *
   * @return all claims.
   */
  @NotNull
  Collection<Claim> all();

  /**
   * loads claim from database.
   *
   * @param uniqueId the unique id to get.
   *
   * @return claim.
   */
  @Nullable
  Claim load(@NotNull UUID uniqueId);

  /**
   * saves the claim to database.
   *
   * @param claim the claim to save.
   */
  void save(@NotNull Claim claim);

  /**
   * saves all the claims.
   *
   * @param claims the claims to save.
   */
  void saveAll(@NotNull Collection<Claim> claims);
}
