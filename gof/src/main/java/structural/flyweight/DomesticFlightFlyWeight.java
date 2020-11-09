package structural.flyweight;

public class DomesticFlightFlyWeight implements FlightFlyWeight {
    private final String market;
    private final AircraftHeavyObject aircraftHeavyObject;

    public DomesticFlightFlyWeight(String market, AircraftHeavyObject aircraftHeavyObject) {
        this.market = market;
        this.aircraftHeavyObject = aircraftHeavyObject;
    }

    @Override
    public AircraftHeavyObject getAircraft() {
        return this.aircraftHeavyObject;
    }

    public String getMarket() {
        return market;
    }
}
