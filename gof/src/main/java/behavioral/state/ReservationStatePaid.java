package behavioral.state;

public class ReservationStatePaid implements ReservationState {
    private double fare;

    public ReservationStatePaid(double fare) {
        this.fare = fare;
    }

    @Override
    public Reservation.State getState() {
        return Reservation.State.PAID;
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
        reservation.setState(new ReservationStateOnHold());
    }

    @Override
    public double fare() {
        return this.fare;
    }

    @Override
    public void ticket(Reservation reservation) {
        reservation.setState(new ReservationStateTicketed(this.fare));
    }
}
