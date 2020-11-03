public class CoreReservationFactory {
    public static CoreReservation of(String typeOfReservation) {
        if (typeOfReservation.equals("NonRev")) {
            return new CoreReservationNonRev();
        } else {
            return new CoreReservationDefault();
        }
    }
}
