package creational.abstractfactory;

public class VehicleFerrari implements Vehicle {
    @Override
    public String brand() {
        return "FERRARI";
    }

    @Override
    public double price() {
        return 5000000D;
    }
}
