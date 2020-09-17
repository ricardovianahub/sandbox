package domain;

public enum CarBodyType {
    SEDAN(20),
    VAN(50),
    CONVERTIBLE(100),
    MINIVAN(50);

    private double baseFare;

    CarBodyType(double baseFare) {
        this.baseFare = baseFare;
    }

    public double getBaseFare() {
        return baseFare;
    }
}
