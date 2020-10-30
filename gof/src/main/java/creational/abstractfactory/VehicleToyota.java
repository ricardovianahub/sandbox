package creational.abstractfactory;

public class VehicleToyota implements Vehicle {
    @Override
    public String brand() {
        return "TOYOTA";
    }

    @Override
    public double price() {
        return 2000D;
    }
}
