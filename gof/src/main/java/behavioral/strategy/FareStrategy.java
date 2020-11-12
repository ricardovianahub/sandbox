package behavioral.strategy;

public interface FareStrategy {
    double calculateFare(double fare);

    static FareStrategy aa20Fare() {
        return fare -> fare * 0.8;
    }

    static FareStrategy nonRevFare() {
        return fare -> 0d;
    }

    static FareStrategy normalFare() {
        return fare -> fare;
    }
}
