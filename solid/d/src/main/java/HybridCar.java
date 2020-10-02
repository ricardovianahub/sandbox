public class HybridCar implements Car {
    public HybridCar(Make honda) {

    }

    @Override
    public double distanceByGallonsOfGas(int gallons) {
        return 200 + gallons * 20;
    }

    @Override
    public Make getMake() {
        return null;
    }
}
