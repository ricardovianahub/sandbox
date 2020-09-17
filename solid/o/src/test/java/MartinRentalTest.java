import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

import domain.CarBodyType;
import domain.Customer;

public class MartinRentalTest {

    @Test
    void baseFareForEachBodyType() {
        assertEquals(20,
                CarBodyType.SEDAN.getBaseFare()
        );
        assertEquals(50,
                CarBodyType.VAN.getBaseFare()
        );
        assertEquals(100,
                CarBodyType.CONVERTIBLE.getBaseFare()
        );
        assertEquals(50,
                CarBodyType.MINIVAN.getBaseFare()
        );
    }

    @ParameterizedTest
    @EnumSource(CarBodyType.class)
    void fareReturnsBaseFareIfNoSpecialConditionsAreMet(CarBodyType type) {
        MartinRental martinRental = new MartinRental();
        assertEquals(type.getBaseFare(),
                martinRental.fare(
                        new Customer("John", "Smith",
                                "IAH", "ORD", "999",
                                type)
                )
        );
    }

    @ParameterizedTest
    @CsvSource({
            "Martin, VAN, 47.5",
            "Jones, VAN, 50",
            "Smith, SEDAN, 20",
            "Martin, SEDAN, 19"
    })
    void fareApplies5PercentDiscountIfLastNameIsMartin(String lastName, CarBodyType type, double expected) {
        MartinRental martinRental = new MartinRental();
        assertEquals(expected,
                martinRental.fare(
                        new Customer("Joe", lastName,
                                "IAH", "ORD", "999",
                                type)
                )
        );
    }

    @ParameterizedTest
    @CsvSource({
            "ORD, CONVERTIBLE, 100",
            "YYZ, CONVERTIBLE, 110"
    })
    void fareApplies10FeeIfLuxury(String origin, CarBodyType type, double expected) {
        MartinRental martinRental = new MartinRental();
        assertEquals(expected,
                martinRental.fare(
                        new Customer("Joe", "Schmo",
                                origin, "ORD", "999",
                                type)
                )
        );
    }

    @ParameterizedTest
    @CsvSource({
            "HAV, VAN, 52.5",
            "ORD, VAN, 50"
    })
    void fareApplies10percentFeeIfHavana(String destination, CarBodyType type, double expected) {
        MartinRental martinRental = new MartinRental();
        assertEquals(expected,
                martinRental.fare(
                        new Customer("Joe", "Schmo",
                                "MIA", destination, "999",
                                type)
                )
        );
    }

    @Test
    void fareDiscounts15PercentIfComingFromDFWAiport() {
        MartinRental martinRental = new MartinRental();

        String firstName = "John";
        String lastName = "Smith";
        String origin = "DFW";
        String destination = "ORD";
        String flightNumber = "999";
        CarBodyType type = CarBodyType.MINIVAN;
        Customer customer = new Customer(
                firstName, lastName, origin, destination, flightNumber, type
        );

        assertEquals(50 * 0.85, martinRental.fare(customer), 0.001);
    }
}
