package behavioral.template;

public class Reservation {
    private double fare;

    public Reservation(double fare) {
        this.fare = fare;
    }

    public double calculatedFare() {
        return this.fare;
    }
}
