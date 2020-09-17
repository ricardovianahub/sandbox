package farecalculation;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

import domain.Customer;

public interface FareCalculation {
    double apply(Customer customer, double fare);

    static List<FareCalculation> factory() {
        return new ArrayList<FareCalculation>() {{
            add(new FareCalculationDFWDiscount());
            add(new FareCalcutationLastNameMartin());
            add(new FareCalculationYYZConvertible());
            add(new FareCalculationDestinationHavana());
        }};
    }
}
