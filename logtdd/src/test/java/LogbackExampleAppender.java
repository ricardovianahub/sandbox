import java.util.ArrayList;
import java.util.List;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

public class LogbackExampleAppender extends AppenderBase<ILoggingEvent> {
    static List<ILoggingEvent> events = new ArrayList<>();

    @Override
    protected void append(ILoggingEvent eventObject) {
        events.add(eventObject);
    }
}
