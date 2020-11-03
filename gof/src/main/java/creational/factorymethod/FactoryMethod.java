package creational.factorymethod;

public class FactoryMethod {
    private String message;

    private FactoryMethod() {}

    private FactoryMethod(String message) {
        this.message = message;
    }

    public static FactoryMethod getInstance() {
        return getInstance("DEFAULT");
    }

    public static FactoryMethod getInstance(String message) {
        return new FactoryMethod(message);
    }

    public String message() {
        return this.message;
    }
}
