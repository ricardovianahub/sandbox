package behavioral.interpreter;

public interface Romans {
    String[][] ONES = {{"I", "1"}, {"V", "5"}, {"X", "10"}};
    String[][] TENS = {{"X", "10"}, {"L", "50"}, {"C", "100"}};

    int interpret(String roman);

    static Romans decoder(String[][] symbols) {
        return roman -> {
            if ((symbols[0][0] + symbols[1][0]).equals(roman)) {
                return Integer.parseInt(symbols[1][1])
                        - Integer.parseInt(symbols[0][1]);
            }
            if ((symbols[0][0] + symbols[2][0]).equals(roman)) {
                return Integer.parseInt(symbols[2][1])
                        - Integer.parseInt(symbols[0][1]);
            }
            if (roman.startsWith(symbols[1][0])) {
                return Integer.parseInt(symbols[1][1]) + Romans.decoder(symbols).interpret(roman.substring(1));
            }
            return roman.startsWith(symbols[0][0])
                    ? Integer.parseInt(symbols[0][1]) + Romans.decoder(symbols).interpret(roman.substring(1))
                    : 0;
        };
    }

}
