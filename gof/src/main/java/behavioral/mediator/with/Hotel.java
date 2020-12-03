package behavioral.mediator.with;

public class Hotel {
    private boolean reserved;
    private final Mediator mediator;

    public Hotel(Mediator mediator) {
        this.mediator = mediator;
        this.reserved = false;
    }

    public boolean isHotelReserved() {
        return this.reserved;
    }

    public void createHotelReservation() {
        this.reserved = true;
    }

    public void assignRoomUnavailable() {
        this.reserved = false;
        mediator.assignRoomUnavailable();
    }
}
