package behavioral.strategy_absfactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StrategyAbsFactoryTest {

    @Test
    void calculatedFareWithNormalFareStrategyReturnsBaseFare() {
        Reservation reservation =
                Reservation.newInstance(Reservation.NORMAL, 100.00d);

        assertEquals(100.00d, reservation.calculatedFare());
    }

    @Test
    void calculatedFareWithAA20FareStrategyReturnsBaseFareMinus20Percent() {
        Reservation reservation =
                Reservation.newInstance(Reservation.AA20, 100.00d);

        assertEquals(80.00d, reservation.calculatedFare());
    }

    @Test
    void calculatedFareWithNonRevFareStrategyReturnsZero() {
        Reservation reservation =
                Reservation.newInstance(Reservation.NONREV, 100.00d);

        assertEquals(0.00d, reservation.calculatedFare());
    }
}
