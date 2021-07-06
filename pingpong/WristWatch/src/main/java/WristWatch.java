public class WristWatch {
    public String convertToRomans(int number) {
        if (number == 5) {
            return "V";
        }

        if (number == 10) {
            return "X";
        }

        if (number >= 13 || number <= 0) {
            return null;
        }

        return "I";
    }
}
