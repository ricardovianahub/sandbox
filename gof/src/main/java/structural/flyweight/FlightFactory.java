package structural.flyweight;

public class FlightFactory {

    private static AircraftHeavyObject aircraftHeavyObject;

    static {
        aircraftHeavyObject = new AircraftHeavyObject("737MAX", 150);
    }

    public static FlightFlyWeight getInstance(String market) {
        return new DomesticFlightFlyWeight(market, aircraftHeavyObject);
    }
}
