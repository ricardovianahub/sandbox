package structural.proxy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class HelloClientTest {

    @Test
    void helloClientDoitReturnsHelloFromRemoteService() {
        HelloClient helloClient = new HelloClient();

        assertEquals("hello", helloClient.doit());
    }
}
