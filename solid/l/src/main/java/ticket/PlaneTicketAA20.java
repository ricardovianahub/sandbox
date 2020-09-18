package ticket;

public class PlaneTicketAA20 extends PlaneTicket {
    public PlaneTicketAA20(String origin, String destination, String duration, double cost) {
        super(origin, destination, duration, cost);
    }

    @Override
    public double fare() {
        return super.fare() * 0.8;
    }
}
