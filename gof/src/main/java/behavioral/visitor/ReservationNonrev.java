package behavioral.visitor;

public class ReservationNonrev extends Reservation {
    @Override
    void accept(ReservationVisitor reservationVisitor) {
        reservationVisitor.visit(this);
    }
}
