package basic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LiskovTest {

    @Test
    void animalSpeakTest() {
        Animal animal = new Animal();
        assertEquals("(sound)", animal.speak());
    }

    @Test
    void cowSpeakTest() {
        Animal animal = new Cow();
        assertEquals("moo", animal.speak());
    }

    @Test
    void catSpeakTest() {
        Animal animal = new Cat();
        assertEquals("meow", animal.speak());
    }

    @Test
    void dogSpeakTest() {
        Animal animal = new Dog();
        assertEquals("kick", animal.speak());
    }

}
