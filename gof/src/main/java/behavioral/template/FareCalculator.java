package behavioral.template;

abstract public class FareCalculator {

    protected double fare;
    protected double factor;

    public FareCalculator() {
        this(1);
    }

    public FareCalculator(double factor) {
        this.factor = factor;
    }

    public double calc(double fare) {
        return fare * this.factor;
    }
}
