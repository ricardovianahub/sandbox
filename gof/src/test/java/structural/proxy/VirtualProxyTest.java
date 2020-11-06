package structural.proxy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class VirtualProxyTest {

    @Test
    void virtualProxyLoadsHelloWhenCalled() {
        VirtualProxy virtualProxy = new VirtualProxy();

        assertEquals("hello", virtualProxy.doit());

    }
}
