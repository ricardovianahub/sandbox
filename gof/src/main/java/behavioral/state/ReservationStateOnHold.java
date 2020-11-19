package behavioral.state;

public class ReservationStateOnHold implements ReservationState {
    @Override
    public Reservation.State getState() {
        return Reservation.State.ON_HOLD;
    }

    @Override
    public boolean eligibleForRefund() {
        return false;
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
        return 0;
    }

    @Override
    public void pay(Reservation reservation, double fare) {
        reservation.setState(new ReservationStatePaid(fare));
    }
}
