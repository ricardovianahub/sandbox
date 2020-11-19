package behavioral.state;

public interface ReservationState {
    Reservation.State getState();

    boolean eligibleForRefund();

    boolean canBeChangedForFree();

    void placeOnHold(Reservation reservation);

    double fare();

    default boolean allowedToBoard() {
        return false;
    }

    default void ticket(Reservation reservation) {
        throw new IllegalStateException("Reservation cannot be ticketed when its state is " + reservation.getState());
    }

    default void pay(Reservation reservation, double fare) {

    }
}
