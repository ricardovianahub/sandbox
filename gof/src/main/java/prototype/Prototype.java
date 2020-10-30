package prototype;

public class Prototype implements Cloneable {
    private final String one;
    private final String two;

    @Override
    protected Prototype clone() throws CloneNotSupportedException {
        return (Prototype) super.clone();
    }

    public Prototype(String one, String two) {
        this.one = one;
        this.two = two;
    }

    public String getOne() {
        return one;
    }

    public String getTwo() {
        return two;
    }
}
