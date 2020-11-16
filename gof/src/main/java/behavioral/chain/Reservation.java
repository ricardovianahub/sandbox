package behavioral.chain;

public class Reservation {
    private final String originAirport;
    private final double baseFare;

    public Reservation(String originAirport, double baseFare) {

        this.originAirport = originAirport;
        this.baseFare = baseFare;
    }

    public String getOriginAirport() {
        return originAirport;
    }

    public double getBaseFare() {
        return baseFare;
    }
}
