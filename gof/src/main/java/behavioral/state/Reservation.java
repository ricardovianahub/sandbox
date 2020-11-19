package behavioral.state;

public class Reservation {

    private ReservationState reservationState;
    private String firstName;
    private String lastName;

    private Reservation(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.reservationState = new ReservationStateInProgress();
    }

    void setState(ReservationState reservationState) {
        this.reservationState = reservationState;
    }

    public static Reservation newReservationInProgress(String firstName, String lastName) {
        return new Reservation(firstName, lastName);
    }

    public State getState() {
        return this.reservationState.getState();
    }

    public boolean eligibleForRefund() {
        return this.reservationState.eligibleForRefund();
    }

    public boolean canBeChangedForFree() {
        return this.reservationState.canBeChangedForFree();
    }

    public Reservation placeOnHold() {
        this.reservationState.placeOnHold(this);
        return this;
    }

    public void pay(double fare) {
        this.reservationState.pay(this, fare);
    }

    public double fare() {
        return this.reservationState.fare();
    }

    public boolean allowedToBoard() {
        return this.reservationState.allowedToBoard();
    }

    public void ticket() {
        this.reservationState.ticket(this);
    }

    public enum State {ON_HOLD, PAID, TICKETED, IN_PROGRESS}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
