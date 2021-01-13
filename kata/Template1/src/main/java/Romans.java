public class Romans {

    public static final int SIZE_OF_CLOCK = 12;
    private final int[] numbers =
            new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

    public String convert(int number) {
        if (number > SIZE_OF_CLOCK) {
            String[] romans = new String[]{
                    "X", "XX", "XXX", "XL", "L"
            };
            String roman = findRoman(number / 10, romans);
            if (String.valueOf(number).endsWith("5") ) {
                roman = roman + "V";
            }
            if (roman != null) {
                return roman;
            }
        }
        String[] romans = new String[]{
                "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX",
                "X", "XI", "XII"
        };
        return findRoman(number, romans);
    }

    private String findRoman(int number, String[] romans) {
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == number) {
                return romans[i];
            }
        }
        return null;
    }
}
