import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ReservationTest {

    @Test
    void reservationInstantiation() {
        Reservation reservation = new Reservation(
                "ABCDEF",
                1500.00,
                "John",
                "Smith"
        );
        assertEquals("ABCDEF", reservation.getRecordLocator());
        assertEquals(1500.00, reservation.getBaseFare());
        assertEquals("John", reservation.getFirstName());
        assertEquals("Smith", reservation.getLastName());
    }

    @ParameterizedTest
    @CsvSource({
            "C, 300, 330.00",
            "C, 100, 110.00",
            "B, 200, 240.00",
            "B, 100, 120.00"
    })
    void fareByType(String type, double baseFare, double expected) {
        SalesReservation reservation = new Reservation(
                "ABCDEF",
                baseFare,
                "John",
                "Smith"
        );

        assertEquals(expected, reservation.fare(type), 0.001);
    }

    @ParameterizedTest
    @CsvSource({
            "Abc,Def,defa",
            "Jane,Doe,doej",
            "John,Smith,smithj"

    })
    void userCode(String firstName, String lastName, String expected ) {
        LoyaltyReservation reservation = new Reservation(
                "ABCDEF",
                100,
                firstName,
                lastName
        );

        assertEquals(expected, reservation.userCode());
    }
}
