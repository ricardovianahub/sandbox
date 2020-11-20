package behavioral.template;

public class FareCalculatorAA20 extends FareCalculator {

    public FareCalculatorAA20() {
        super();
    }

    public FareCalculatorAA20(double fare) {
        super(fare);
    }

    @Override
    public double calc(double fare) {
        return fare * 0.8 * this.factor;
    }
}
