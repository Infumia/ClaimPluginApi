package tr.com.infumia.claimplugin.paper.api.member;

import java.util.UUID;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine member creators.
 */
public interface MemberCreator {

  /**
   * creates a claim member.
   *
   * @param uniqueId the unique id to create.
   *
   * @return a newly created claim member.
   */
  @NotNull
  Member createMember(@NotNull UUID uniqueId);

  /**
   * creates a claim owner.
   *
   * @param uniqueId the unique id to create.
   *
   * @return a newly created claim owner.
   */
  @NotNull
  Member createOwner(@NotNull UUID uniqueId);
}
