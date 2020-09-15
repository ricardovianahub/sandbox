import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class MathCalculationTest {

    @Test
    void numbersOutOfRangeWillThrowException() {
        MathCalculation mathCalculation = new MathCalculation();
        int argument = 0;

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> mathCalculation.apply(argument)
        );
    }

    @ParameterizedTest
    @MethodSource("numberRange1to100Data")
    void numberRange1to100(int argument, double expected) {
        MathCalculation mathCalculation = new MathCalculation();
        double actual = mathCalculation.apply(argument);

        assertEquals(expected, actual, 0.000000000001);
    }
    static Stream<Arguments> numberRange1to100Data() {
        return Stream.of(
                Arguments.of(1, 1.9),
                Arguments.of(2, 2.8),
                Arguments.of(10, 10),
                Arguments.of(100, 91),

                Arguments.of(101, 76.8),
                Arguments.of(200, 156),

                Arguments.of(201, 148.7),
                Arguments.of(300, 218)
        );
    }

}
