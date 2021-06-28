package tr.com.infumia.claimplugin.paper.api.member;

import java.util.UUID;
import java.util.function.Function;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine member creators.
 */
@FunctionalInterface
public interface MemberCreator extends Function<@NotNull UUID, @NotNull Member> {

}
