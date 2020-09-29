public class CarDefault implements Car {
    private double milesTraveled;

    public CarDefault(Make make, double milesTraveled) {
        this.milesTraveled = milesTraveled;
    }

    @Override
    public double distanceByGallonsOfGas(int gallons) {
        return this.milesTraveled * gallons;
    }

    @Override
    public Make getMake() {
        return Make.TOYOTA;
    }
}
