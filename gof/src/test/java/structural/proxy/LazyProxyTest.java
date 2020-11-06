package structural.proxy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LazyProxyTest {

    @Test
    void virtualProxyLoadsHelloWhenCalled() {
        LazyProxy lazyProxy = new LazyProxy();

        assertEquals("hello", lazyProxy.doit());

    }
}
