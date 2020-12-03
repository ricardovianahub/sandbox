package behavioral.visitor;

public class ReservationVisitorMarketingName implements ReservationVisitor {
    @Override
    public void visit(ReservationAA20 reservationAA20) {
        reservationAA20.setName("AA20 is the best");
    }

    @Override
    public void visit(ReservationNonrev reservationNonrev) {
        reservationNonrev.setName("Non-rev is for shmucks");
    }

    @Override
    public void visit(ReservationDefault reservationDefault) {
        reservationDefault.setName("Standard is good");
    }
}
