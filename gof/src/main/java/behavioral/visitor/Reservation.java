package behavioral.visitor;

public abstract class Reservation {

    private String name;

    abstract void accept(ReservationVisitor reservationVisitor);

    final String getName() {
        return this.name;
    }
    final void setName(String name) {
        this.name = name;
    }
}
