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
                recordLocator, cost, firstName, lastName
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
                recordLocator, cost, firstName, lastName
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
                recordLocator, cost, firstName, lastName
        );

        assertEquals("Traveller, Joe",
                NameFormatter.formatName(reservation)
        );
    }

}
