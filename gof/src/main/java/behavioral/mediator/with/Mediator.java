package behavioral.mediator.with;

public class Mediator {
    private AirReservation airReservation;
    private Hotel hotel;
    private Voucher voucher;

    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void setAirReservation(AirReservation airReservation) {
        this.airReservation = airReservation;
    }

    public void voucherAssignIssued() {
        this.voucher.assignIssued();
    }

    public void airReservationChangeStatus(AirReservation.Status status) {
        this.airReservation.changeStatus(status);
    }

    public void hotelCreateHotelReservation() {
        this.hotel.createHotelReservation();
    }

    public void assignRoomUnavailable() {
        voucherAssignIssued();
        airReservationChangeStatus(AirReservation.Status.DELAYED_OVERNIGHT_WITH_VOUCHER);
    }
}
