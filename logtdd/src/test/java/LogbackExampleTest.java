import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LogbackExampleTest {

    @Test
    void ensureThatGenerateLogEntryWritesToLoggerRelatedToLogbackExampleClass() {
        LogbackExample logbackExample = new LogbackExample();

        logbackExample.generateLogEntry("Correct result");

        assertEquals(1, LogbackExampleAppender.events.size());
        assertEquals("Correct result", LogbackExampleAppender.events.get(0).getMessage());
    }

}
