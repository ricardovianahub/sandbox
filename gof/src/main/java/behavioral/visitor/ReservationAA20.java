package behavioral.visitor;

public class ReservationAA20 extends Reservation {
    @Override
    public void accept(ReservationVisitor reservationVisitor) {
        reservationVisitor.visit(this);
    }
}
