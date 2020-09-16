import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PhraseTest {

    @Test
    void receiveNameAsAParameter() {
        Phrase phrase = new Phrase("name", "name");
        assertEquals("name", phrase.getName());
    }

    @Test
    void receiveCityAsAParameter() {
        Phrase phrase = new Phrase("name", "city");
        assertEquals("city", phrase.getCity());
    }

    @Test
    void showOutpuPhrase() {
        Phrase phrase = new Phrase("name", "city");
        assertEquals("NAME is from city", phrase.showPhraseBasedOnPattern());
    }

    @Test
    void showOutpuPhrase2() {
        Phrase phrase = new Phrase("John", "Dallas");
        assertEquals("JOHN is from Dallas", phrase.showPhraseBasedOnPattern());
    }
}
