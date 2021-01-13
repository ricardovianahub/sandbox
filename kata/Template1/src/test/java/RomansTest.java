import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class RomansTest {

    // Romans: I - 1; V - 5; X - 10; L - 50; C - 100; D - 500; M - 1000
    // Combine up to 3 of the letters that mean a number which start with "1" - III XXX CCC MMM --- but not VVV LLL DDD
    // If a number that starts with "1" is to the left for a higher value letter (anyone), it subtracts - XC = 90 - CM = 900; IV = 4; XL = 40
    // You can combine letter expression to generate intermediary values

    // Romans up to 3
    // Romans for hours in a clock
    // Romans for elevator floor stops - building has 50 floors
    //      - Show the floors ending in 0 - 10, 20, 30, 40, 50
    //      - Show the floors ending in 5 - 5, 15, 25, 35, 45
    //      - Show the floors ending in 1 and 6
    //      - Show the floors ending in 2, 3, 7 and 8
    //      - Floors ending in 4 and 9
    // All Romans; fail if less than 1 or greater than 3999

    @ParameterizedTest
    @MethodSource("clockData")
    void twelveHoursClock(int number, String expected) {
        Romans romans = new Romans();
        String actual = romans.convert(number);

        assertEquals(expected, actual);
    }
    static Stream<Arguments> clockData() {
        return Stream.of(
          Arguments.of(1, "I"),
          Arguments.of(2, "II"),
          Arguments.of(3, "III"),
          Arguments.of(4, "IV"),
          Arguments.of(5, "V"),
          Arguments.of(6, "VI"),
          Arguments.of(7, "VII"),
          Arguments.of(8, "VIII"),
          Arguments.of(9, "IX"),
          Arguments.of(10, "X"),
          Arguments.of(11, "XI"),
          Arguments.of(12, "XII")
        );
    }

    @ParameterizedTest
    @MethodSource("elevatorData")
    void fiftyFloorsForElevator(int number, String expected) {
        Romans romans = new Romans();
        String actual = romans.convert(number);

        assertEquals(expected, actual);
    }
    static Stream<Arguments> elevatorData() {
        return Stream.of(
                Arguments.of(5, "V"),
                Arguments.of(10, "X"),
                Arguments.of(15, "XV"),
                Arguments.of(20, "XX"),
                Arguments.of(25, "XXV"),
                Arguments.of(30, "XXX"),
                Arguments.of(35, "XXXV"),
                Arguments.of(40, "XL"),
                Arguments.of(50, "L")
        );
    }

}
