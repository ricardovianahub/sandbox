public class Animal {
    private final String name;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String fly() {
        return "go up";
    }

    public String eat(String food) {
        return "yum";
    }
}
