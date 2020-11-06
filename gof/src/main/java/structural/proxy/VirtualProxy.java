package structural.proxy;

public class VirtualProxy {

    private String hello;

    public VirtualProxy() {
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
