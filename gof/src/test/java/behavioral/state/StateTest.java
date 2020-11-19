package behavioral.state;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class StateTest {

    @Test
    void reservationInitialStateIsInProcess() {
        Reservation reservation = Reservation.newReservationInProgress("John", "Doe");

        assertTrue(reservation.canBeChangedForFree());
        assertEquals(Reservation.State.IN_PROGRESS, reservation.getState());
        assertFalse(reservation.eligibleForRefund());
        assertFalse(reservation.allowedToBoard());
        assertEquals("John", reservation.getFirstName());
        assertEquals("Doe", reservation.getLastName());
    }

    @Test
    void reservationIsPlacedOnHold() {
        Reservation reservation = Reservation.newReservationInProgress("John", "Doe");
        reservation.placeOnHold();

        assertFalse(reservation.canBeChangedForFree());
        assertEquals(Reservation.State.ON_HOLD, reservation.getState());
        assertFalse(reservation.eligibleForRefund());
        assertFalse(reservation.allowedToBoard());
    }

    @Test
    void reservationIsPaidThenPlacedBackOnHold() {
        Reservation reservation = Reservation.newReservationInProgress("John", "Doe");
        reservation.pay(100d);

        assertFalse(reservation.canBeChangedForFree());
        assertEquals(Reservation.State.PAID, reservation.getState());
        assertTrue(reservation.eligibleForRefund());
        assertFalse(reservation.allowedToBoard());
        assertEquals(100d, reservation.fare());

        reservation.placeOnHold();
        assertEquals(0d, reservation.fare());
    }

    @Test
    void reservationIsTicketed() {
        Reservation reservation = Reservation.newReservationInProgress("John", "Doe");
        reservation.pay(100d);
        reservation.ticket();

        assertFalse(reservation.canBeChangedForFree());
        assertEquals(Reservation.State.TICKETED, reservation.getState());
        assertTrue(reservation.eligibleForRefund());
        assertTrue(reservation.allowedToBoard());
        assertEquals(100d, reservation.fare());
    }

    @Test
    void reservationIsPlacedOnHoldThenPaid() {
        Reservation reservation = Reservation.newReservationInProgress("John", "Doe");
        reservation.placeOnHold();
        reservation.pay(100d);

        assertEquals(Reservation.State.PAID, reservation.getState());
        assertEquals(100d, reservation.fare());
    }

    @Test
    void ticketingAReservationNotPaidThrowsAnIllegalStateException() {
        assertThrows(IllegalStateException.class,
                () -> Reservation.newReservationInProgress("John", "Doe").ticket()
        );
        assertThrows(IllegalStateException.class,
                () -> Reservation.newReservationInProgress("John", "Doe").placeOnHold().ticket()
        );
    }

//    @Test
    void paidReservationCannotBePaidAgain() {
        Reservation reservation = Reservation.newReservationInProgress("John", "Doe");
        reservation.pay(100d);
        assertThrows(IllegalStateException.class,
                () -> reservation.pay(100d)
        );
    }

//    @Test
    void ticketedReservationCannotBePaidAgain() {
        Reservation reservation = Reservation.newReservationInProgress("John", "Doe");
        reservation.pay(100d);
        reservation.ticket();
        assertThrows(IllegalStateException.class,
                () -> reservation.pay(100d)
        );
    }

//    void reservationMessageShouldBeCurrentInterfaceValue() {
//        Reservation reservation = Reservation.newReservationInProgress();
//        assertEquals(ReservationMessage.CURRENT_MESSAGE, reservation.getMessage());
//    }
}
