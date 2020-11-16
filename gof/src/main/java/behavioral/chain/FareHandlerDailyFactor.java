package behavioral.chain;

public class FareHandlerDailyFactor implements FareHandler {
    private final double dailyFactor;

    public FareHandlerDailyFactor(double dailyFactor) {
        this.dailyFactor = dailyFactor;
    }

    @Override
    public double fareCalculation(String originAirport, double baseFare) {
        return baseFare * this.dailyFactor;
    }
}
