public class HybridCar implements Car {
    @Override
    public double distanceByGallonsOfGas(int gallons) {
        return 200 + gallons * 20;
    }

    @Override
    public Make getMake() {
        return null;
    }
}
