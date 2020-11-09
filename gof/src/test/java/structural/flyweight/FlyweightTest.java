package structural.flyweight;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class FlyweightTest {

    @Test
    void instanceOfFlyWeightViaFactoryAndBridge() {
        FlightFlyWeight flightFlyWeight = FlightFactory.getInstance("DFWLAX");

        AircraftHeavyObject aircraftHeavyObject = flightFlyWeight.getAircraft();

        assertEquals("737MAX", aircraftHeavyObject.getName());
        assertEquals(150, aircraftHeavyObject.getCapacity());
    }

    @Test
    void differentFlightsSameAircraftObject() {
        FlightFlyWeight flightFlyWeight1 = FlightFactory.getInstance("DFWLAX");
        FlightFlyWeight flightFlyWeight2 = FlightFactory.getInstance("DFWLAX");
        FlightFlyWeight flightFlyWeight3 = FlightFactory.getInstance("DFWLAX");

        assertEquals(flightFlyWeight1.getAircraft(), flightFlyWeight2.getAircraft());
        assertEquals(flightFlyWeight1.getAircraft(), flightFlyWeight3.getAircraft());
        assertNotEquals(flightFlyWeight1, flightFlyWeight2);
        assertNotEquals(flightFlyWeight1, flightFlyWeight3);
    }

}
