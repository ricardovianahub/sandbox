import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbackExample {
    public void generateLogEntry(String message) {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.debug(message);
    }
}
