package structural.proxy;

public class HelloClient {

    private HelloService helloService;

    public HelloClient() {
        this.helloService = new HelloService();
    }

    public String doit() {
        return this.helloService.doit();
    }
}
