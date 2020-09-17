package farecalculation;

import domain.CarBodyType;
import domain.Customer;

class FareCalculationYYZConvertible implements FareCalculation {

    @Override
    public double apply(Customer customer, double fare) {
        if (customer.getType().equals(CarBodyType.CONVERTIBLE)
                && customer.getOrigin().equals("YYZ")
        ) {
            fare = fare + 10;
        }
        return fare;
    }
}
