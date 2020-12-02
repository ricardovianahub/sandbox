package behavioral.visitor;

public class ReservationVisitorShortName implements ReservationVisitor {
    @Override
    public void visit(ReservationAA20 reservationAA20) {
        reservationAA20.setName("AA20");
    }

    @Override
    public void visit(ReservationNonrev reservationNonrev) {
        reservationNonrev.setName("Non-rev");
    }
}
