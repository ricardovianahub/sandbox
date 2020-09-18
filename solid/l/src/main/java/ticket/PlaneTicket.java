package ticket;

public class PlaneTicket {
    private String origin;
    private String destination;
    private String type;
    private double cost;

    public PlaneTicket(String origin, String destination, String type,
                       double cost) {
        this.origin = origin;
        this.destination = destination;
        this.type = type;
        this.cost = cost;
    }
    public double fare() {
        if ("DFW".equals(this.origin)) {
            return this.cost * 0.9;
        }
        return this.cost;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getType() {
        return type;
    }

    public double getCost() {
        return cost;
    }
}
