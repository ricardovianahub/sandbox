import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ReservationTest {

    @Test
    void reservationInstantiation() {
        String recordLocator = "SDFIOP";
        double cost = 0.00;
        String firstName = "Joe";
        String lastName = "Traveller";
        Reservation reservation = new Reservation(
                recordLocator, cost, firstName, firstName, lastName
        );

        assertEquals("SDFIOP", reservation.getRecordLocator());
    }

    @Test
    void displayNameFormat() {
        String recordLocator = "SDFIOP";
        double cost = 0.00;
        String firstName = "Joe";
        String lastName = "Traveller";
        Reservation reservation = new Reservation(
                recordLocator, cost, firstName, firstName, lastName
        );

        assertEquals("Traveller, Joe", reservation.formattedName());
    }

    @Test
    void displayNameFormatWithUtilityClass() {
        String recordLocator = "SDFIOP";
        double cost = 0.00;
        String firstName = "Joe";
        String lastName = "Traveller";
        Reservation reservation = new Reservation(
                recordLocator, cost, firstName, firstName, lastName
        );

        assertEquals("Traveller, Joe",
                NameFormatter.formatName(reservation)
        );
    }

    @Test
    void promotionalSale() {
        String recordLocator = "SDFIOP";
        double cost = 100.00;
        String firstName = "Joe";
        String lastName = "Traveller";
        String originAirport = "DFW";
        Reservation reservation = new Reservation(
                recordLocator, cost, firstName, lastName, originAirport
        );

        double discountPercentage = 20.00;
        PromotionalSale promotionalSale = new PromotionSaleOct2020(discountPercentage);

        assertEquals(80, promotionalSale.apply(reservation.getCost()));
        assertEquals( 90, reservation.costBasedOnOriginAirport());
    }

}
