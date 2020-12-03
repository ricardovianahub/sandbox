package behavioral.mediator.with;

public class RentalCar {
    private final Mediator mediator;
    private boolean reserved;

    public RentalCar(Mediator mediator) {
        this.reserved = false;
        this.mediator = mediator;
    }

    public boolean isRentalCarReserved() {
        return this.reserved;
    }

    public void createRentalCarReservation() {
        this.reserved = true;
    }

    public void cancelReservation() {
        this.reserved = false;
    }
}
