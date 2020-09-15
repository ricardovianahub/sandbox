package net.ricardoviana.romans.v05;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RomansTest {

    private Romans romans;

    @BeforeEach
    public void beforeEach() {
        romans = new Romans();
    }

    @Test
    public void handling0or4000() {
        assertThrows(IllegalArgumentException.class,
                () -> romans.from(0)
        );
        assertThrows(IllegalArgumentException.class,
                () -> romans.from(4000)
        );
    }

    @Test
    public void handling1() {
        assertEquals("I", romans.from(1));
    }

    @Test
    public void handling2() {
        assertEquals("II", romans.from(2));
    }

    @Test
    public void handling3() {
        assertEquals("III", romans.from(3));
    }

    @Test
    public void handling4() {
        assertEquals("IV", romans.from(4));
    }

    @Test
    public void handling5() {
        assertEquals("V", romans.from(5));
    }

    @Test
    public void handling6() {
        assertEquals("VI", romans.from(6));
    }

    @Test
    public void handling7() {
        assertEquals("VII", romans.from(7));
    }

    @Test
    public void handling8() {
        assertEquals("VIII", romans.from(8));
    }

    @Test
    public void handling9() {
        assertEquals("IX", romans.from(9));
    }

    @Test
    public void handling10() {
        assertEquals("X", romans.from(10));
    }

    @Test
    public void handling29() {
        assertEquals("XXIX", romans.from(29));
    }

    @Test
    public void handling38() {
        assertEquals("XXXVIII", romans.from(38));
    }

    @Test
    public void handling47() {
        assertEquals("XLVII", romans.from(47));
    }

    @Test
    public void handling56() {
        assertEquals("LVI", romans.from(56));
    }

    @Test
    public void handling65() {
        assertEquals("LXV", romans.from(65));
    }

    @Test
    public void handling74() {
        assertEquals("LXXIV", romans.from(74));
    }

    @Test
    public void handling83() {
        assertEquals("LXXXIII", romans.from(83));
    }

    @Test
    public void handling92() {
        assertEquals("XCII", romans.from(92));
    }

    @Test
    public void handling101() {
        assertEquals("CI", romans.from(101));
    }

    @Test
    public void handling298() {
        assertEquals("CCXCVIII", romans.from(298));
    }

    @Test
    public void handling387() {
        assertEquals("CCCLXXXVII", romans.from(387));
    }

    @Test
    public void handling476() {
        assertEquals("CDLXXVI", romans.from(476));
    }

    @Test
    public void handling565() {
        assertEquals("DLXV", romans.from(565));
    }

    @Test
    public void handling654() {
        assertEquals("DCLIV", romans.from(654));
    }

    @Test
    public void handling743() {
        assertEquals("DCCXLIII", romans.from(743));
    }

    @Test
    public void handling832() {
        assertEquals("DCCCXXXII", romans.from(832));
    }

    @Test
    public void handling921() {
        assertEquals("CMXXI", romans.from(921));
    }

    @Test
    public void handling1234() {
        assertEquals("MCCXXXIV", romans.from(1234));
    }

    @Test
    public void handling2345() {
        assertEquals("MMCCCXLV", romans.from(2345));
    }

    @Test
    public void handling3999() {
        assertEquals("MMMCMXCIX", romans.from(3999));
    }

}
