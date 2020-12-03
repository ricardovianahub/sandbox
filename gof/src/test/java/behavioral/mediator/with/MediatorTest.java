package behavioral.mediator.with;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MediatorTest {

    @Test
    void defineReservationHotelVoucher() {
        Mediator mediator = new Mediator();

        Voucher voucher = new Voucher(mediator);
        Hotel hotel = new Hotel(mediator);
        AirReservation airReservation = new AirReservation(mediator);

        mediator.setVoucher(voucher);
        mediator.setHotel(hotel);
        mediator.setAirReservation(airReservation);

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
