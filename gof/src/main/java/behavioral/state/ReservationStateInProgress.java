package behavioral.state;

public class ReservationStateInProgress implements ReservationState {
    @Override
    public Reservation.State getState() {
        return Reservation.State.IN_PROGRESS;
    }

    @Override
    public boolean eligibleForRefund() {
        return false;
    }

    @Override
    public boolean canBeChangedForFree() {
        return true;
    }

    @Override
    public void placeOnHold(Reservation reservation) {
        reservation.setState(new ReservationStateOnHold());
    }

    @Override
    public double fare() {
        return 0;
    }

    @Override
    public void pay(Reservation reservation, double fare) {
        reservation.setState(new ReservationStatePaid(fare));
    }
}
