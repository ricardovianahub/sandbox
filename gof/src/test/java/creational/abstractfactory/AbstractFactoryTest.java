package creational.abstractfactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class AbstractFactoryTest {

    @Test
    void emptyObjectIsReturnedIfFactoryIsCalledWithNoArgs() {
        Vehicle vehicle = VehicleAbstractFactory.getInstance();
        assertNotNull(vehicle);
        assertEquals("DEFAULT", vehicle.brand());
        assertEquals(0D, vehicle.price());
    }

    @Test
    void toyotaIsReturnedForSmartcar() {
        Vehicle vehicle = VehicleAbstractFactory.getInstance("SMARTCAR");
        assertNotNull(vehicle);
        assertEquals("TOYOTA", vehicle.brand());
        assertEquals(2000D, vehicle.price());
    }

    @Test
    void ferrariIsReturnedForExpensivecar() {
        Vehicle vehicle = VehicleAbstractFactory.getInstance("EXPENSIVECAR");
        assertNotNull(vehicle);
        assertEquals("FERRARI", vehicle.brand());
        assertEquals(5000000D, vehicle.price());
    }

}
