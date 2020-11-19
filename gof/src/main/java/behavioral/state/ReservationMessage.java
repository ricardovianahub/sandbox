package behavioral.state;

public interface ReservationMessage {
    String CURRENT_MESSAGE = "This is a message";

    default String getMessage() {
        return CURRENT_MESSAGE;
    }
}
