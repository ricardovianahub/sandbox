package behavioral.visitor;

public class ReservationVisitorLongName implements ReservationVisitor {
    @Override
    public void visit(ReservationAA20 reservationAA20) {
        reservationAA20.setName("Reservation AA20 - 20% off for employees");
    }

    @Override
    public void visit(ReservationNonrev reservationNonrev) {
        reservationNonrev.setName("Reservation Non-revenue - free for employees");
    }
}
