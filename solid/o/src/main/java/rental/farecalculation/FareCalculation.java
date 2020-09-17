package rental.farecalculation;

import rental.domain.Customer;

public interface FareCalculation {
    double apply(Customer customer, double fare);

    static FareCalculation[] factory() {
        return new FareCalculation[]{
                new FareCalculationDFWDiscount(),
                new FareCalcutationLastNameMartin(),
                new FareCalculationYYZConvertible()
        };
    }
}
