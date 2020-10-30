package creational.abstractfactory;

public class VehicleAbstractFactory  {
    public static Vehicle getInstance() {
        return getInstance("");
    }

    public static Vehicle getInstance(String query) {
        switch (query) {
            case "SMARTCAR" : return new VehicleToyota();
            case "EXPENSIVECAR" : return new VehicleFerrari();
            default : return new VehicleDefault();
        }
    }
}
