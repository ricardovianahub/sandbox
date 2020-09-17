package encrypt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class EncryptTest {

    @Test
    void invalidTypeThrowsException() {
        Encrypt encrypt = new Encrypt();
        String input = null;
        assertThrows(IllegalArgumentException.class,
                () -> encrypt.it("??INVALID??", input)
        );
    }

    @ParameterizedTest
    @CsvSource({
            "PLUS1,ABC,BCD",
            "PLUS1,GHI,HIJ",
            "PLUS1,XYZ,YZA",
            "MINUS1,GHI,FGH",
            "MINUS1,BCD,ABC",
            "MINUS1,ABC,ZAB",
            "ROT13,TESTING,GRFGVAT",
            "ROT13,ANOTHER,NABGURE",
            "ROT13,TIME,GVZR",
            "CYPHER01,ABCD,FGHB"
    })
    void encription(String type, String input, String expected) {
        Encrypt encrypt = new Encrypt();

        String actual = encrypt.it(type, input);

        assertEquals(expected, actual);
    }

}
