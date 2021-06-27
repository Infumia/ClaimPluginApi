package tr.com.infumia.claimplugin.paper.api.member;

import java.util.UUID;
import java.util.function.Function;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine member providers.
 */
@FunctionalInterface
public interface MemberProvider extends Function<@NotNull UUID, @NotNull Member> {

}
