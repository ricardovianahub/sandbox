package behavioral.visitor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class VisitorTest {

    @Test
    void reservationAA20Names() {
        Reservation reservation = new ReservationAA20();
        reservation.accept(new ReservationVisitorLongName());
        assertEquals("Reservation AA20 - 20% off for employees", reservation.getName());
        reservation.accept(new ReservationVisitorShortName());
        assertEquals("AA20", reservation.getName());
    }

    @Test
    void reservationNonRevNames() {
        Reservation reservation = new ReservationNonrev();
        reservation.accept(new ReservationVisitorLongName());
        assertEquals("Reservation Non-revenue - free for employees", reservation.getName());
        reservation.accept(new ReservationVisitorShortName());
        assertEquals("Non-rev", reservation.getName());
    }

}
