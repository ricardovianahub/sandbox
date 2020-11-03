package creational.singleton;

public class Singleton {
    private static final Singleton singleton = new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
        return singleton;
    }
}
