package com.example.loop.bus;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = GeneratorApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GeneratorApplicationTests {

    public static final String EVENT_SIGNATURE = "calc";

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Value("${targetURL}")
    private String targetURL;

    @Test
    void heartbeat() {
        String response = testRestTemplate.getForObject("http://ricbox.com/bus/heartbeat", String.class);
        assertEquals("alive", response);
    }

    @ParameterizedTest
    @CsvSource({
            "3!x!4,12",
            "5!x!6,30"
    })
    void sendEventsInSequence(String request, String expected) throws InterruptedException {
        testRestTemplate.getForObject(
                "http://ricbox.com/bus/sendRequestEvent/calc/" + request, String.class
        );

        processOtherNode();

        String result = testRestTemplate.getForObject(
                "http://ricbox.com/bus/popNextResponseEvent/calc", String.class
        );
        assertEquals(expected, result);

        String emptyResult = testRestTemplate.getForObject(
                "http://ricbox.com/bus/popNextResponseEvent/calc", String.class
        );
        assertEquals("[empty]", emptyResult);
    }

    @ParameterizedTest
    @CsvSource({
            "3!x!4,12,5!x!6,30",
            "4!x!4,16,5!x!5,25"
    })
    void sendEventsOutOfSequence(String request1, String expected1, String request2, String expected2) throws InterruptedException {
        sendEvent("http://ricbox.com/bus/sendRequestEvent/calc/", request1);
        sendEvent("http://ricbox.com/bus/sendRequestEvent/calc/", request2);

        processOtherNode();
        processOtherNode();

        String result1 = sendEvent("http://ricbox.com/bus/popNextResponseEvent/", EVENT_SIGNATURE);
        assertEquals(expected1, result1);
        String result2 = sendEvent("http://ricbox.com/bus/popNextResponseEvent/", EVENT_SIGNATURE);
        assertEquals(expected2, result2);

        String emptyResult1 = sendEvent("http://ricbox.com/bus/popNextResponseEvent/", EVENT_SIGNATURE);
        assertEquals("[empty]", emptyResult1);
        String emptyResult2 = sendEvent("http://ricbox.com/bus/popNextResponseEvent/", EVENT_SIGNATURE);
        assertEquals("[empty]", emptyResult2);
    }

    private String sendEvent(String url, String request) {
        return testRestTemplate.getForObject(url + request, String.class);
    }

    private void processOtherNode() throws InterruptedException {
        Thread.sleep(300);
        String payload = testRestTemplate.getForObject("http://ricbox.com/bus/popNextRequestEvent/calc", String.class);
        String[] request = payload.split(",");
        String originalEventId = request[0];
        String eventContent = request[1];
        String[] calc = eventContent.split("!");
        int result;
        if (calc[1].equals("x")) {
            result = Integer.parseInt(calc[0]) * Integer.parseInt(calc[2]);
            testRestTemplate.getForObject(
                    String.format(
                            "http://ricbox.com/bus/sendResponseEvent/calc/%s/%s", originalEventId, result
                    ), String.class
            );
        }
    }

}
