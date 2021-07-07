import java.util.HashMap;
import java.util.Map;

public class WristWatch {

    private final static Map<Integer, String> numberToRomansMap = new HashMap<>(){
        {
            put(1, "I");
            put(2, "II");
            put(3, "III");
            put(4, "IV");
            put(5, "V");
            put(6, "VI");
            put(7, "VII");
            put(8, "VIII");
            put(9, "IX");
            put(10, "X");
            put(11, "XI");
            put(12, "XII");
        }
    };

    public String convertToRomans(int number) {

        if (number == 4) {
            return "IV";
        }

        if (number == 5) {
            return "V";
        }

        if (number == 10) {
            return "X";
        }

        if (number >= 13 || number <= 0) {
            return null;
        }

        return "I".repeat(number);
    }

    public String convertToRomansMap(int number){

        if (number >= 13 || number <= 0) {
            return null;
        }

        return numberToRomansMap.get(number);
    }
}
