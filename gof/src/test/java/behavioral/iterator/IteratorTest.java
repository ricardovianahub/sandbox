package behavioral.iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class IteratorTest {

    @ParameterizedTest
    @MethodSource("myCollectionClasses")
    void iteratorSameResultsDifferentImplementations(MyCollection myCollection) {
        myCollection.add("Hello");
        myCollection.add("North");
        myCollection.add("Texas");

        StringBuilder sb = new StringBuilder();

        MyIterator myIterator = myCollection.newIterator();

        while (myIterator.hasNext()) {
            sb.append(myIterator.next());
        }

        assertEquals("HelloNorthTexas", sb.toString());
    }

    static Stream<Arguments> myCollectionClasses() {
        return Stream.of(
                Arguments.of(new MyCollectionOne()),
                Arguments.of(new MyCollectionTwo(3)),
                Arguments.of(new MyCollectionThree())
        );
    }
}
