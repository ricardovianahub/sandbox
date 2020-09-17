package rental.farecalculation;

import rental.domain.Customer;

class FareCalcutationLastNameMartin implements FareCalculation {
    @Override
    public double apply(Customer customer, double fare) {
        if (customer.getLastName().equals("Martin")) {
            fare = fare - fare * 0.05;
        }
        return fare;
    }
}
