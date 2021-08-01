package tr.com.infumia.claimplugin.paper.api.event;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.claimplugin.paper.api.claim.ParentClaim;
import tr.com.infumia.claimplugin.paper.api.home.Home;

/**
 * an abstract class that represents claim home events.
 */
abstract class ClaimHomeEvent extends ClaimEvent {

  /**
   * the home.
   */
  @NotNull
  @Getter
  @Setter
  private Home home;

  /**
   * ctor.
   *
   * @param claim the claim.
   * @param home the home.
   */
  ClaimHomeEvent(@NotNull final ParentClaim claim, @NotNull final Home home) {
    super(claim);
    this.home = home;
  }
}
