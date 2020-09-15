import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.sun.org.apache.xpath.internal.Arg;

import domain.DomainObject;
import serviceone.DomainObjectTransformer2;
import serviceone.InputObject;

public class DomainObjectTransformer2Test {

    private DomainObjectTransformer2 domainObjectTransformer2;

    @BeforeEach
    void beforeEach() {
        domainObjectTransformer2 = new DomainObjectTransformer2();
    }

    @Test
    void transformThrowsExceptionWhenInputObjectIsNull() {
        InputObject inputObject = null;

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    DomainObject domainObject =
                            domainObjectTransformer2.transform(inputObject);
                }
        );
    }

    @Test
    void transformThrowsExceptionWhenInputObjectIdIsNull() {
        InputObject inputObject = new InputObject(null, "something");

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    DomainObject domainObject =
                            domainObjectTransformer2.transform(inputObject);
                }
        );

    }

    @ParameterizedTest
    @MethodSource("transformIdData")
    void transformShouldReturnIntegerWhenInputObjectIdIsNotNull(String input, int expected) {
        InputObject inputObject = new InputObject(input, "something");
        DomainObject domainObject =
                domainObjectTransformer2.transform(inputObject);
        assertEquals(expected, domainObject.getId());
    }

    static Stream<Arguments> transformIdData() {
        return Stream.of(
                Arguments.of("1", 1),
                Arguments.of("  2", 2),
                Arguments.of("3  ", 3),
                Arguments.of("0000004", 4),
                Arguments.of("   000005   ", 5)
        );
    }

    @Test
    void transformThrowsExceptionWhenInputObjectNameIsNull() {
        InputObject inputObject = new InputObject("010", null);

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    DomainObject domainObject =
                            domainObjectTransformer2.transform(inputObject);
                }
        );

    }

    // “NAME” – “Name”
    @ParameterizedTest
    @MethodSource("transformNameData")
    void transformShouldReturnProperStringWhenInputObjectIsNotNull(String input, String expected) {
        InputObject inputObject = new InputObject("010", input);
        DomainObject domainObject =
                domainObjectTransformer2.transform(inputObject);
        assertEquals(expected, domainObject.getName());
    }

    static Stream<Arguments> transformNameData() {
        return Stream.of(
                Arguments.of("NAME", "Name"),
                Arguments.of("wRONGCAPS", "Wrongcaps"),
                Arguments.of("S P A C E D", "Spaced"),
                Arguments.of("    tRiMMed  ", "Trimmed")
        );
    }

}
