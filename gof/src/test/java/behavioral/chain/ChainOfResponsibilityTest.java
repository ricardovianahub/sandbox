package behavioral.chain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ChainOfResponsibilityTest {

    // Requirements
    // 5% Percent Discount = DFW
    // Add Flat fee $10 = LAX
    // Multiply by daily factor = all

    @Test
    void priceWithDiscountOfApply5PercentDiscountIfDFW() {
        Reservation reservation = new Reservation("DFW", 100d);

        FareClient fareClient = new FareClient();

        assertEquals(95d, fareClient.priceWithDiscountOf(reservation));
    }

    @Test
    void priceWithDiscountOfAddFlatFee10IfLAX() {
        Reservation reservation = new Reservation("LAX", 100d);

        FareClient fareClient = new FareClient();

        assertEquals(110d, fareClient.priceWithDiscountOf(reservation));
    }

    @Test
    void priceWithDiscountOfMultiplyByDailyFactor() {
        assertEquals(90d,
                new FareClient(0.9d)
                        .priceWithDiscountOf(new Reservation("ORD", 100d)));
        assertEquals(85.5d,
                new FareClient(0.9d)
                        .priceWithDiscountOf(new Reservation("DFW", 100d)));
        assertEquals(99d,
                new FareClient(0.9d)
                        .priceWithDiscountOf(new Reservation("LAX", 100d)));
    }

    @Test
    void returnBaseFareIfOther() {
        Reservation reservation = new Reservation("ZZZ", 100d);

        FareClient fareClient = new FareClient();

        assertEquals(100d, fareClient.priceWithDiscountOf(reservation));
    }

}
