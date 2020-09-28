public class CarDefault implements Car {
    private double milesTraveled;

    public CarDefault(double milesTraveled) {
        this.milesTraveled = milesTraveled;
    }

    @Override
    public double withGallons(int gallons) {
        return this.milesTraveled * gallons;
    }
}
