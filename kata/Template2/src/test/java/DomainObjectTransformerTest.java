import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import domain.DomainObject;
import serviceone.DomainObjectTransformer;
import serviceone.InputObject;

public class DomainObjectTransformerTest {

    @Test
    void transformThrowsExceptionWhenInputIsNull() {
        DomainObjectTransformer domainObjectTransformer = new DomainObjectTransformer();

        InputObject argument = null;

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    final DomainObject actual = domainObjectTransformer.transform(argument);
                }
        );
    }

    @Test
    void transformReturnsValidDomainObjectWhenValidInputObjectIsProvided() {
        DomainObjectTransformer domainObjectTransformer = new DomainObjectTransformer();

        InputObject argument = new InputObject("001", "FIRST");

        DomainObject actual = domainObjectTransformer.transform(argument);

        assertEquals(1, actual.getId());
        assertEquals("First", actual.getName());
    }

    @Test
    void transformThrowsExceptionWhenNameIsNull() {
        DomainObjectTransformer domainObjectTransformer = new DomainObjectTransformer();

        InputObject argument = new InputObject("001", null);

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    final DomainObject actual = domainObjectTransformer.transform(argument);
                }
        );
    }

    @ParameterizedTest
    @MethodSource("transformNameData")
    void transformRetunsValidDomainObjectFollowingNameRules(String inputValue, String transformedValue) {
        DomainObjectTransformer domainObjectTransformer = new DomainObjectTransformer();

        InputObject argument = new InputObject("001", inputValue);

        DomainObject actual = domainObjectTransformer.transform(argument);

        assertEquals(1, actual.getId());
        assertEquals(transformedValue, actual.getName());
    }

    static Stream<Arguments> transformNameData() {
        return Stream.of(
                Arguments.of("cApsAllOver", "Capsallover"),
                Arguments.of("S P A C E S", "Spaces"),
                Arguments.of("Spre ad Arou Nd", "Spreadaround"),
                Arguments.of("   TRImmEd      ", "Trimmed")
        );
    }
}
