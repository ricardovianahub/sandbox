package domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DomainObjectTest {

    @Test
    void getNameSignatureReturnsIdAndName() {
        assertEquals("6 - something",
                new DomainObject(6, "something").getNameSignature()
        );
        assertEquals("9 - Somethingelse",
                new DomainObject(9, "Somethingelse").getNameSignature()
        );
    }
}
