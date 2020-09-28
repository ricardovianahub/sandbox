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
        Car car = new CarDefault(milesPerGallon);
        
        assertEquals(expected, car.withGallons(gallons));
    }

    @Test
    void superCarConsumption() {
        Car car = new SuperCar();

        assertEquals(50, car.withGallons(4));
        assertEquals(50, car.withGallons(7));
        assertEquals(50, car.withGallons(8));
        assertEquals(50, car.withGallons(9));
    }


}
