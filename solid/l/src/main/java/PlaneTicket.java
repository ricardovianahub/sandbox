public class PlaneTicket {
    private String origin;
    private String destination;
    private String duration;
    private double cost;

    public PlaneTicket(String origin, String destination, String duration,
                       double cost) {
        this.origin = origin;
        this.destination = destination;
        this.duration = duration;
        this.cost = cost;
    }

    public double fare() {
        if ("DFW".equals(origin)) {
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

    public String getDuration() {
        return duration;
    }

    public double getCost() {
        return cost;
    }
}
