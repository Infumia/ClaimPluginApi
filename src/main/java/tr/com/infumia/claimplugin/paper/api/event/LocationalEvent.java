package tr.com.infumia.claimplugin.paper.api.event;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tr.com.infumia.claimplugin.paper.api.claim.ParentClaim;

/**
 * an interface to determine locational events.
 */
public interface LocationalEvent {

  /**
   * creates a locational event.
   *
   * @param event the event to create.
   *
   * @return locational event.
   */
  @NotNull
  static LocationalEvent of(@NotNull final ClaimEvent event) {
    return LocationalEvent.of(event.getClaim(), event);
  }

  /**
   * creates a locational event.
   *
   * @param event the event to create.
   * @param actor the actor to create.
   *
   * @return locational event.
   */
  @NotNull
  static LocationalEvent of(@NotNull final ClaimEvent event, @Nullable final Player actor) {
    return LocationalEvent.of(event.getClaim(), event, actor);
  }

  /**
   * creates a locational event.
   *
   * @param claim the claim to create.
   * @param event the event to create.
   *
   * @return locational event.
   */
  @NotNull
  static LocationalEvent of(@NotNull final ParentClaim claim, @NotNull final Event event) {
    return LocationalEvent.of(claim, event, null, claim.getClaimBlockLocation());
  }

  /**
   * creates a locational event.
   *
   * @param claim the claim to create.
   * @param event the event to create.
   * @param actor the actor to create.
   *
   * @return locational event.
   */
  @NotNull
  static LocationalEvent of(@NotNull final ParentClaim claim, @NotNull final Event event,
                            @Nullable final Player actor) {
    return LocationalEvent.of(claim, event, actor, claim.getClaimBlockLocation());
  }

  /**
   * creates a locational event.
   *
   * @param claim the claim to create.
   * @param event the event to create.
   * @param location the location to create.
   *
   * @return locational event.
   */
  @NotNull
  static LocationalEvent of(@NotNull final ParentClaim claim, @NotNull final Event event,
                            @NotNull final Location location) {
    return LocationalEvent.of(claim, event, null, location);
  }

  /**
   * creates a locational event.
   *
   * @param claim the claim to create.
   * @param event the event to create.
   * @param actor the actor to create.
   * @param location the location to create.
   *
   * @return locational event.
   */
  @NotNull
  static LocationalEvent of(@NotNull final ParentClaim claim, @NotNull final Event event,
                            @Nullable final Player actor, @NotNull final Location location) {
    return new Impl(claim, event, actor, location);
  }

  /**
   * cancels the event, if {@link #getEvent()} is a {@link Cancellable}.
   */
  void cancelEvent();

  /**
   * obtains the actor.
   *
   * @return actor.
   */
  @Nullable
  Player getActor();

  /**
   * sets the actor.
   *
   * @param actor the actor to set.
   */
  void setActor(@Nullable Player actor);

  /**
   * obtains the claim.
   *
   * @return claim.
   */
  @NotNull
  ParentClaim getClaim();

  /**
   * obtains the event.
   *
   * @return event.
   */
  @NotNull
  Event getEvent();

  /**
   * obtains the location.
   *
   * @return location.
   */
  @NotNull
  Location getLocation();

  /**
   * sets the location.
   *
   * @param location the location to set.
   */
  void setLocation(@NotNull Location location);

  /**
   * a simple implementation of {@link LocationalEvent}.
   */
  @Getter
  @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  final class Impl implements LocationalEvent {

    /**
     * the claim.
     */
    @NotNull
    private final ParentClaim claim;

    /**
     * the event.
     */
    @NotNull
    private final Event event;

    /**
     * the actor.
     */
    @Nullable
    @Setter
    private Player actor;

    /**
     * the location.
     */
    @NotNull
    @Setter
    private Location location;

    @Override
    public void cancelEvent() {
      if (this.event instanceof Cancellable) {
        ((Cancellable) this.event).setCancelled(true);
      }
    }
  }
}
