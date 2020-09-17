package farecalculation;

import domain.Customer;

class FareCalculationDestinationHavana implements FareCalculation {

    @Override
    public double apply(Customer customer, double fare) {
        if (customer.getDestination().equals(("HAV"))) {
            fare = fare + fare * 0.05;
        }
        return fare;
    }
}
