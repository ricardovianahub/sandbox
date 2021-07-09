import java.time.LocalDateTime;

public class LocalServerTime implements OfficialTime {
    @Override
    public LocalDateTime current() {
        return LocalDateTime.now();
    }
}
