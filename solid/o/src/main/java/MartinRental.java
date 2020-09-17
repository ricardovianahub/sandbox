import java.util.List;

import domain.Customer;
import farecalculation.FareCalculation;

public class MartinRental {

    private List<FareCalculation> calculations;

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
