package structural.proxy;

public class LazyProxy {

    private String hello;

    public LazyProxy() {
        this.hello = null;
    }

    public String doit() {
        if (this.hello == null) {
            this.hello = loadHeavyContent();
        }
        return this.hello;
    }

    private String loadHeavyContent() {
        return "hello";
    }
}
