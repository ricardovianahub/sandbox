package behavioral.strategy;

public class Reservation {
    private FareStrategy strategy;
    private double fare;

    public Reservation(FareStrategy strategy, double fare) {
        this.strategy = strategy;
        this.fare = fare;
    }

    public double calculatedFare() {
        return this.strategy.calculateFare(this.fare);
    }

    public void setStrategy(FareStrategy fareStrategy) {
        this.strategy = fareStrategy;
    }
}
