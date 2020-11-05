package structural.bridge;

public class FlightAssigner {
    private FlightPrioritizer flightPrioritizer;

    public FlightAssigner(FlightPrioritizer flightPrioritizer) {
        this.flightPrioritizer = flightPrioritizer;
    }

    public String whoGoesFirst() {
        return this.flightPrioritizer.whoGoesFirst();
    }
}
