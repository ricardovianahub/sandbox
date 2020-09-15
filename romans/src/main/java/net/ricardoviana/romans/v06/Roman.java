package net.ricardoviana.romans.v06;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Roman {
    private static final Map<Integer, String> ROMANS_TABLE =
            Collections.unmodifiableMap(
                    new LinkedHashMap<Integer, String>() {{
                        put(1000, "M");
                        put(900, "CM");
                        put(500, "D");
                        put(400, "CD");
                        put(100, "C");
                        put(90, "XC");
                        put(50, "L");
                        put(40, "XL");
                        put(10, "X");
                        put(9, "IX");
                        put(5, "V");
                        put(4, "IV");
                        put(1, "I");
                    }}
            );

    public static String of(int number) {
        StringBuilder sb = new StringBuilder();
        int numberRemaining = number;
        for (int key : ROMANS_TABLE.keySet()) {
            while (numberRemaining >= key) {
                sb.append(ROMANS_TABLE.get(key));
                numberRemaining -= key;
            }
        }
        return sb.toString();
    }

}
