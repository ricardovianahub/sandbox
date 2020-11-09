package structural.flyweight;

public class AircraftHeavyObject {
    private String name;
    private int capacity;

    public AircraftHeavyObject(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
