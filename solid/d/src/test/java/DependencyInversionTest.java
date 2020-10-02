import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

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
        Car car = new GasCar(Car.Make.TOYOTA, milesPerGallon);
        
        assertEquals(expected, car.distanceByGallonsOfGas(gallons));
    }

    @Test
    void hydrogenConsumption() {
        Car car = new HydrogenCar(Car.Make.TOYOTA);

        assertEquals(50, car.distanceByGallonsOfGas(4));
        assertEquals(50, car.distanceByGallonsOfGas(7));
        assertEquals(50, car.distanceByGallonsOfGas(8));
        assertEquals(50, car.distanceByGallonsOfGas(9));
    }

    @Test
    void hybridCar() {
        Car car = new HybridCar(Car.Make.HONDA);

        assertEquals( 200, car.distanceByGallonsOfGas(0));
        assertEquals( 220, car.distanceByGallonsOfGas(1));
        assertEquals( 240, car.distanceByGallonsOfGas(2));
    }

    @Test
    void carsShouldHaveMake() {
        Car car = new GasCar(Car.Make.TOYOTA, 10);
        assertEquals(Car.Make.TOYOTA, car.getMake());
    }

    @Test
    void reportOnMileageWith2Cars() {
        // I would like to produce a report on the mileage of each Make
        // Make
        List<Car> cars = new ArrayList<>();

        cars.add(new GasCar(Car.Make.TOYOTA, 10));
        cars.add(new GasCar(Car.Make.HONDA, 20));
        cars.add(new HybridCar(Car.Make.HONDA));
        cars.add(new HydrogenCar(Car.Make.TOYOTA));

        ReportOnCars reportOnCars = new ReportOnCars(cars);

        assertEquals(4, reportOnCars.totalNumberOfCars());
        assertEquals(2, reportOnCars.applicableNumberOfCars());
    }

    @Test
    void reportOnMileageWith3Cars() {
        // I would like to produce a report on the mileage of each Make
        // Make
        List<Car> cars = new ArrayList<>();

        cars.add(new GasCar(Car.Make.TOYOTA, 10));
        cars.add(new GasCar(Car.Make.HONDA, 20));
        cars.add(new GasCar(Car.Make.HONDA, 30));
        cars.add(new HydrogenCar(Car.Make.TOYOTA));
        cars.add(new HydrogenCar(Car.Make.TOYOTA));

        ReportOnCars reportOnCars = new ReportOnCars(cars);

        assertEquals(5, reportOnCars.totalNumberOfCars());
        assertEquals(3, reportOnCars.applicableNumberOfCars());
    }
}

/// Option 1
/// Mileage applies only to gas
/// Add different atttribute to identify if a car is powered by gas or not
///
/// Option 2
/// Mileage applies to anything that makes the car move
/// In case of hydrogen, the measure is by power cells
///
/// Option 3
/// Concern with cost requires:
/// Cost of powercell
/// Cost of gallon of gas
/// Attribute that indicates which type of fuel that car uses


/// Option 1 - Bring up mileage to the interface, plus a new attribute that identifies GAS
/// Option 2 - Use GasCar instead of Car
