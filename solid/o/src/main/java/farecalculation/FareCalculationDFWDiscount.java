package farecalculation;

import domain.Customer;

class FareCalculationDFWDiscount implements FareCalculation {
    @Override
    public double apply(Customer customer, double fare) {
        if (customer.getOrigin().equals("DFW")) {
            fare = fare - fare * 0.15;
        }
        return fare;
    }
}
