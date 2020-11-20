package behavioral.template;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TemplateTest {

    @Test
    void calculatedFareWithNormalFareTemplateReturnsBaseFare() {
        FareCalculator fareCalculator = new FareCalculatorDefault();
        Reservation reservation = new Reservation(fareCalculator.calc(100.00d));

        assertEquals(100.00d, reservation.calculatedFare());
    }

    @Test
    void calculatedFareWithNormalFareTemplateReturnsBaseFareWithFactor09() {
        FareCalculator fareCalculator = new FareCalculatorDefault(0.9);
        Reservation reservation = new Reservation(fareCalculator.calc(100.00d));

        assertEquals(90.00d, reservation.calculatedFare());
    }

    @Test
    void calculatedFareWithAA20FareTemplateReturnsBaseFareMinus20Percent() {
        FareCalculator fareCalculator = new FareCalculatorAA20();
        Reservation reservation = new Reservation(fareCalculator.calc(100.00d));

        assertEquals(80.00d, reservation.calculatedFare());
    }

    @Test
    void calculatedFareWithAA20FareTemplateReturnsBaseFareMinus20PercentFactor09() {
        FareCalculator fareCalculator = new FareCalculatorAA20(0.9);
        Reservation reservation = new Reservation(fareCalculator.calc(100.00d));

        assertEquals(72.00d, reservation.calculatedFare());
    }

    @Test
    void calculatedFareWithNonRevFareTemplateReturnsZero() {
        FareCalculator fareCalculator = new FareCalculatorNonRev();

        Reservation reservation = new Reservation(fareCalculator.calc(100.00d));
        assertEquals(0.00d, reservation.calculatedFare());
    }

    @Test
    void calculatedFareWithNonRevFareTemplateReturnsZeroFactor09() {
        FareCalculator fareCalculator = new FareCalculatorNonRev(0.9);

        Reservation reservation = new Reservation(fareCalculator.calc(100.00d));
        assertEquals(0.00d, reservation.calculatedFare());
    }

}
