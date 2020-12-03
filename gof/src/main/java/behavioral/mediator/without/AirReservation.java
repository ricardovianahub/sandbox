package behavioral.mediator.without;

public class AirReservation {
    private final Hotel hotel;
    private final Voucher voucher;
    private Status status;

    public AirReservation(Hotel hotel, Voucher voucher) {
        this.hotel = hotel;
        this.voucher = voucher;
        this.status = Status.TICKETED;
    }

    public void changeStatus(Status status) {
        if (Status.DELAYED_OVERNIGHT.equals(status)) {
            hotel.createHotelReservation();
        }
        this.status = status;
    }

    public Status getStatus() {
        return this.status;
    }

    public enum Status {TICKETED, DELAYED_OVERNIGHT_WITH_VOUCHER, DELAYED_OVERNIGHT}
}
