package behavioral.strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StrategyTest {

    @Test
    void calculatedFareWithNormalFareStrategyReturnsBaseFare() {
        FareStrategy strategy = FareStrategy.normalFare();
        Reservation reservation = new Reservation(strategy, 100.00d);

        assertEquals(100.00d, reservation.calculatedFare());
    }

    @Test
    void calculatedFareWithAA20FareStrategyReturnsBaseFareMinus20Percent() {
        FareStrategy strategy = FareStrategy.aa20Fare();
        Reservation reservation = new Reservation(strategy, 100.00d);

        assertEquals(80.00d, reservation.calculatedFare());
    }

    @Test
    void calculatedFareWithNonRevFareStrategyReturnsZero() {
        FareStrategy strategy = FareStrategy.nonRevFare();

        Reservation reservation = new Reservation(strategy, 100.00d);
        assertEquals(0.00d, reservation.calculatedFare());

        reservation.setStrategy(FareStrategy.normalFare());
        assertEquals(100.00d, reservation.calculatedFare());
    }
}
