package behavioral.visitor;

public class ReservationDefault extends Reservation {
    @Override
    void accept(ReservationVisitor reservationVisitor) {
        reservationVisitor.visit(this);
    }
}
