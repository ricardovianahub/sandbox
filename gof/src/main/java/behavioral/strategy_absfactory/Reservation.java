package behavioral.strategy_absfactory;

public class Reservation {
    public static final String AA20 = "AA20";
    public static final String NONREV = "NONREV";
    static final String NORMAL = "NORMAL";
    private final FareStrategy strategy;
    private final double fare;

    private Reservation(FareStrategy strategy, double fare) {
        this.strategy = strategy;
        this.fare = fare;
    }

    public static Reservation newInstance(String strategyLabel, double fare) {
        switch (strategyLabel) {
            case NORMAL:
                return new Reservation(FareStrategy.normalFare(), fare);
            case AA20:
                return new Reservation(FareStrategy.aa20Fare(), fare);
            case NONREV:
                return new Reservation(FareStrategy.nonRevFare(), fare);
        }
        return new Reservation(FareStrategy.normalFare(), fare);
    }

    public double calculatedFare() {
        return this.strategy.calculateFare(this.fare);
    }
}
