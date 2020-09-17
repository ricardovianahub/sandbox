package rental;

import rental.domain.Customer;
import rental.farecalculation.FareCalculation;

public class MartinRental {

    private final FareCalculation[] calculations;

    public MartinRental() {
        calculations = FareCalculation.factory();
    }

    public double fare(Customer customer) {
        double fare = customer.getType().getBaseFare();

        for (FareCalculation calculation : calculations) {
            fare = calculation.apply(customer, fare);
        }

        return fare;
    }

}
