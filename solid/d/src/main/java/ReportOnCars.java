import java.util.List;

public class ReportOnCars {
    private List<Car> cars;

    public ReportOnCars(List<Car> cars) {
        this.cars = cars;
    }

    public int totalNumberOfCars() {
        return this.cars.size();
    }

    public int applicableNumberOfCars() {
        return (int) cars.stream().filter(c -> c instanceof GasEngine).count();
    }
}

