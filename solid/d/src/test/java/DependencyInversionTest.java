import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DependencyInversionTest {

    @ParameterizedTest
    @CsvSource({
            "22, 2, 44",
            "33, 2, 66",
            "5.5, 2, 11"
    })
    void carFuelConsumption(double milesPerGallon, int gallons, double expected) {
        // I would like to produce a report on the mileage of my
        // various car models
        // Make
        // Model

        Car car = new CarDefault(Car.Make.TOYOTA, milesPerGallon);
        
        assertEquals(expected, car.distanceByGallonsOfGas(gallons));
    }

    @Test
    void hydrogenConsumption() {
        Car car = new HydrogenCar();

        assertEquals(50, car.distanceByGallonsOfGas(4));
        assertEquals(50, car.distanceByGallonsOfGas(7));
        assertEquals(50, car.distanceByGallonsOfGas(8));
        assertEquals(50, car.distanceByGallonsOfGas(9));
    }

    @Test
    void hybridCar() {
        Car car = new HybridCar();
        // on a full battery, the electric car will drive 200 miles
        // before it starts using gas. When it does, the mileage
        // is 20 miles per gallon

        assertEquals( 200, car.distanceByGallonsOfGas(0));
        assertEquals( 220, car.distanceByGallonsOfGas(1));
        assertEquals( 240, car.distanceByGallonsOfGas(2));
    }

    @Test
    void carsShouldHaveMake() {
        Car car = new CarDefault(Car.Make.TOYOTA, 10);
        assertEquals(Car.Make.TOYOTA, car.getMake());
    }

}
