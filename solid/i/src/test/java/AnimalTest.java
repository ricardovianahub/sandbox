import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AnimalTest {

    @Test
    void animalInstantiate() {
        Animal animal = new Animal("Eagle");
        assertEquals("Eagle", animal.getName());
        assertEquals("go up", animal.fly());
        assertEquals("yum", animal.eat("fish"));
    }

}
