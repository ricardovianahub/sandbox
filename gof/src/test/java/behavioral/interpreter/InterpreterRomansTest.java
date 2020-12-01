package behavioral.interpreter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class InterpreterRomansTest {

    @Test
    void ones() {
        assertEquals(1, Romans.decoder(Romans.ONES).interpret("I"));
        assertEquals(2, Romans.decoder(Romans.ONES).interpret("II"));
        assertEquals(3, Romans.decoder(Romans.ONES).interpret("III"));
        assertEquals(4, Romans.decoder(Romans.ONES).interpret("IV"));
        assertEquals(5, Romans.decoder(Romans.ONES).interpret("V"));
        assertEquals(8, Romans.decoder(Romans.ONES).interpret("VIII"));
        assertEquals(9, Romans.decoder(Romans.ONES).interpret("IX"));
    }

    @Test
    void tens() {
        assertEquals(10, Romans.decoder(Romans.TENS).interpret("X"));
        assertEquals(20, Romans.decoder(Romans.TENS).interpret("XX"));
        assertEquals(30, Romans.decoder(Romans.TENS).interpret("XXX"));
        assertEquals(40, Romans.decoder(Romans.TENS).interpret("XL"));
        assertEquals(50, Romans.decoder(Romans.TENS).interpret("L"));
        assertEquals(80, Romans.decoder(Romans.TENS).interpret("LXXX"));
        assertEquals(90, Romans.decoder(Romans.TENS).interpret("XC"));
    }

}
