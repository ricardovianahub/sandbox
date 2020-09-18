public class PlaneTicket {
    private String origin;
    private String destination;
    private String duration;
    private double cost;
    private String discount;

    public PlaneTicket(String origin, String destination, String duration,
                       double cost, String discount) {
        this.origin = origin;
        this.destination = destination;
        this.duration = duration;
        this.cost = cost;
        this.discount = discount;
    }

    public double fare() {
        if ("AA20".equals(this.discount)) {
            return this.cost * 0.8;
        }
        if ("NONREV".equals(this.discount)) {
            return 0;
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

    public String getDiscount() {
        return discount;
    }
}
