package behavioral.mediator.without;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class WithoutMediatorTest {

    @Test
    void defineReservationHotelVoucher() {
        Voucher voucher = new Voucher();
        Hotel hotel = new Hotel(voucher);
        AirReservation airReservation = new AirReservation(hotel, voucher);
        hotel.setAirReservation(airReservation);

        //

        assertFalse(hotel.isHotelReserved());
        assertFalse(voucher.isIssued());
        assertEquals(AirReservation.Status.TICKETED, airReservation.getStatus());

        airReservation.changeStatus(AirReservation.Status.DELAYED_OVERNIGHT);

        assertTrue(hotel.isHotelReserved());
        assertFalse(voucher.isIssued());
        assertEquals(AirReservation.Status.DELAYED_OVERNIGHT, airReservation.getStatus());

        hotel.assignRoomUnavailable();

        assertFalse(hotel.isHotelReserved());
        assertTrue(voucher.isIssued());
        assertEquals(AirReservation.Status.DELAYED_OVERNIGHT_WITH_VOUCHER, airReservation.getStatus());

    }

}
