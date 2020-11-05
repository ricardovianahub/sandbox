package structural.bridge_adapter;

public class FlightAssignerBridge {
    private FlightPrioritizer flightPrioritizer;

    public FlightAssignerBridge(FlightPrioritizer flightPrioritizer) {
        this.flightPrioritizer = flightPrioritizer;
    }

    public String whoGoesFirst() {
        return this.flightPrioritizer.whoGoesFirst();
    }
}
