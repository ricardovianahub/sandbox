public class GasCar implements Car, GasEngine {
    private double mileage;

    @Override
    public double getMileage() {
        return mileage;
    }

    public GasCar(Make make, double mileage) {
        this.mileage = mileage;
    }

    @Override
    public double distanceByGallonsOfGas(int gallons) {
        return this.mileage * gallons;
    }

    @Override
    public Make getMake() {
        return Make.TOYOTA;
    }
}
