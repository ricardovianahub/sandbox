public class HydrogenCar implements Car {
    public HydrogenCar(Make toyota) {

    }

    @Override
    public double distanceByGallonsOfGas(int gallons) {
        return 50;
    }

    @Override
    public Make getMake() {
        return null;
    }
}
