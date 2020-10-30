package creational.factorymethod;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class FactoryMethodTest {

    @Test
    void getInstanceNoArgsReturnsANewInstanceOfFactoryMethod() {
        FactoryMethod factoryMethod1 = FactoryMethod.getInstance("New message");
        FactoryMethod factoryMethod2 = FactoryMethod.getInstance("New message");
        FactoryMethod factoryMethod3 = FactoryMethod.getInstance("New message");

        assertNotNull(factoryMethod1);
        assertNotEquals(factoryMethod1, factoryMethod2);
        assertNotEquals(factoryMethod1, factoryMethod3);
    }

    @Test
    void whenUsingGetInstanceNoArgsMessageReturnsDefault() {
        FactoryMethod factoryMethod = FactoryMethod.getInstance();

        assertEquals("DEFAULT", factoryMethod.message());
    }

    @Test
    void whenUsingGetInstanceWithOneArgThatIsTheMessage() {
        FactoryMethod factoryMethod1 = FactoryMethod.getInstance("New message");
        FactoryMethod factoryMethod2 = FactoryMethod.getInstance("Another MeSSage");

        assertEquals("New message", factoryMethod1.message());
        assertEquals("Another MeSSage", factoryMethod2.message());
    }

}
