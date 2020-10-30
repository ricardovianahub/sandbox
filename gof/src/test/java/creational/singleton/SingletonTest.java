package creational.singleton;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class SingletonTest {

    @Test
    void when3InstancesOfSingletonAreRequestedTheSameInstanceIsReturnedEveryTime() {
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
        Singleton singleton3 = Singleton.getInstance();

        assertNotNull(singleton1);
        assertEquals(singleton1, singleton2);
        assertEquals(singleton1, singleton3);
    }
}
