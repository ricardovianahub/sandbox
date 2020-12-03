package behavioral.mediator.with;

public class AirReservation {
    private final Mediator mediator;
    private Status status;

    public AirReservation(Mediator mediator) {
        this.mediator = mediator;
        this.status = Status.TICKETED;
    }

    public void changeStatus(Status status) {
        if (Status.DELAYED_OVERNIGHT.equals(status)) {
            mediator.hotelCreateHotelReservation();
        }
        this.status = status;
    }

    public Status getStatus() {
        return this.status;
    }

    public enum Status {TICKETED, DELAYED_OVERNIGHT_WITH_VOUCHER, DELAYED_OVERNIGHT}
}
