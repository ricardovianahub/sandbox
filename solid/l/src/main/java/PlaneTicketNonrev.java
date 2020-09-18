public class PlaneTicketNonrev extends PlaneTicket {
    public PlaneTicketNonrev(String origin, String destination, String duration) {
        super(origin, destination, duration, 0);
    }

    @Override
    public double fare() {
        return 0;
    }
}
