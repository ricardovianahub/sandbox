package behavioral.template;

public class FareCalculatorNonRev extends FareCalculator {

    public FareCalculatorNonRev() {
        super();
    }

    public FareCalculatorNonRev(double fare) {
        super(fare);
    }

    @Override
    public double calc(double fare) {
        return 0;
    }
}
