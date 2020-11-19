package behavioral.state;

public class ReservationStateTicketed implements ReservationState {
    private double fare;

    public ReservationStateTicketed(double fare) {
        this.fare = fare;
    }

    @Override
    public Reservation.State getState() {
        return Reservation.State.TICKETED;
    }

    @Override
    public boolean eligibleForRefund() {
        return true;
    }

    @Override
    public boolean canBeChangedForFree() {
        return false;
    }

    @Override
    public void placeOnHold(Reservation reservation) {

    }

    @Override
    public double fare() {
        return this.fare;
    }

    @Override
    public void ticket(Reservation reservation) {

    }

    @Override
    public boolean allowedToBoard() {
        return true;
    }
}
