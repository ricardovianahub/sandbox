package behavioral.visitor;

public interface ReservationVisitor {
    void visit(ReservationAA20 reservationAA20);
    void visit(ReservationNonrev reservationNonrev);
    void visit(ReservationDefault reservationDefault);
}
