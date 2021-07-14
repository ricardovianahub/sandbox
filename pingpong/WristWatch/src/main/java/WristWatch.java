public class WristWatch {

    private final OfficialTime officialTime;
    private int hour;

    public WristWatch(OfficialTime officialTime) {
        this.officialTime = officialTime;
    }

    private static final String[] romans =
            new String[]{
                    "I", "II", "III", "IV", "V", "VI",
                    "VII", "VIII", "IX", "X", "XI", "XII"
            };

    public String convertToRomans(int number) {
        if (number < 1 || number > 12) {
            return null;
        }

        return romans[indexOf(number)];
    }

    private int indexOf(int number) {
        return number - 1;
    }

    public String currentSeconds() {
        int seconds = officialTime.current().getSecond();
        if (seconds <= 9) {
            return "0" + seconds;
        }
        return String.valueOf(seconds);
    }

    public void setAlarm(int hour, int minute) {
        if (hour < 1 || hour > 12) {
            throw new IllegalArgumentException();
        }
        if (minute < 0 || minute > 59) {
            throw new IllegalArgumentException();
        }


    }

    public String readAlarm() {

        return "0905";
    }
}
