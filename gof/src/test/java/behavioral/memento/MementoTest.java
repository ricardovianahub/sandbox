package behavioral.memento;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class MementoTest {

    @Test
    void mementoClassHasSameAttributesAsOriginatorClass() {
        Reservation reservation1 = new Reservation(
                "John", "Doe", "DFW", "LAX", 100d
        );
        Reservation reservation2 = new Reservation(
                "John", "Doe", "DFW", "LAX", 100d
        );

        assertEquals(reservation1, reservation2);
        Reservation.ReservationMemento reservationMemento1 = reservation1.saveToMemento();

        reservation2.setFirstName("Jane");
        reservation2.setFare(80d);
        reservation2.setDestination("ORD");
        assertNotEquals(reservation1, reservation2);
        reservation2.restoreFromMemento(reservationMemento1);
        assertEquals(reservation1, reservation2);
    }

}
