import java.util.HashMap;
import java.util.Map;

public class WristWatch {

    private final OfficialTime officialTime;

    public WristWatch(OfficialTime officialTime) {
        this.officialTime = officialTime;
    }

    private final static Map<Integer, String> numberToRomansMap = new HashMap<>() {
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
        if (number < 1 || number > 12) {
            return null;
        }

        return numberToRomansMap.get(number);
    }

    public String currentSeconds() {
        int seconds = officialTime.current().getSecond();
        if (seconds <= 9) {
            return "0" + seconds;
        }
        return String.valueOf(seconds);
    }
}
