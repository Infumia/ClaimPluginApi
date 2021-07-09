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
  Collection<ParentClaim> all();

  /**
   * deletes the claim from database.
   *
   * @param claim claim to delete.
   */
  void delete(@NotNull ParentClaim claim);

  /**
   * loads claim from database.
   *
   * @param uniqueId the unique id to get.
   *
   * @return claim.
   */
  @Nullable
  ParentClaim load(@NotNull UUID uniqueId);

  /**
   * saves the claim to database.
   *
   * @param claim the claim to save.
   */
  void save(@NotNull ParentClaim claim);

  /**
   * saves all the claims.
   *
   * @param claims the claims to save.
   */
  void saveAll(@NotNull Collection<ParentClaim> claims);
}
