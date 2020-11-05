package structural.bridge_adapter;

public class FlightAssignerAdapterElite {

    private FlightPrioritizer flightPrioritizer;

    public FlightAssignerAdapterElite() {
        flightPrioritizer = new FlightPrioritizerElite();
    }


    public String whoGoesFirst() {
        return this.flightPrioritizer.whoGoesFirst();
    }
}
