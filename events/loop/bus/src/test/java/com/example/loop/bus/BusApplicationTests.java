package com.example.loop.bus;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = BusApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BusApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void heartbeat() {
        String response = testRestTemplate.getForObject(String.format("http://localhost:%d/bus/heartbeat", port), String.class);
        assertEquals("alive", response);
    }

    @ParameterizedTest
    @CsvSource({
            "3!x!4,12",
            "5!x!6,30"
    })
    void sendEventsInSequence(String request1, String expected1) {
        testRestTemplate.getForObject(
                String.format("http://localhost:%d/bus/sendRequestEvent/calc/" + request1, port), String.class
        );

        processOtherNode();

        String result = testRestTemplate.getForObject(
                String.format("http://localhost:%d/bus/popNextResponseEvent/calc", port), String.class
        );
        assertEquals(expected1, result);

        String emptyResult = testRestTemplate.getForObject(
                String.format("http://localhost:%d/bus/popNextResponseEvent/calc", port), String.class
        );
        assertEquals("[empty]", emptyResult);
    }

    @ParameterizedTest
    @CsvSource({
            "3!x!4,12,5!x!6,30"
    })
    void sendEventsOutOfSequence(String request1, String expected1, String request2, String expected2) {
        String clientEventId1 = sendEvent("http://localhost:%d/bus/sendRequestEvent/calc/", request1);
        String clientEventId2 = sendEvent("http://localhost:%d/bus/sendRequestEvent/calc/", request2);

        processOtherNode();
        processOtherNode();

        String result1 = sendEvent("http://localhost:%d/bus/popNextResponseEvent/", "calc");
        assertEquals(expected1, result1);
        String result2 = sendEvent("http://localhost:%d/bus/popNextResponseEvent/", "calc");
        assertEquals(expected2, result2);

        String emptyResult1 = sendEvent("http://localhost:%d/bus/popNextResponseEvent/", "calc");
        assertEquals("[empty]", emptyResult1);
        String emptyResult2 = sendEvent("http://localhost:%d/bus/popNextResponseEvent/", "calc");
        assertEquals("[empty]", emptyResult2);
    }

    private String sendEvent(String url, String request) {
        return testRestTemplate.getForObject(
                String.format(url + request, port), String.class
        );
    }

    private void processOtherNode() {
        String payload = testRestTemplate.getForObject(String.format("http://localhost:%d/bus/popNextRequestEvent/calc", port), String.class);
        String[] request = payload.split(",");
        String originalEventId = request[0];
        String eventContent = request[1];
        String[] calc = eventContent.split("!");
        int result;
        if (calc[1].equals("x")) {
            result = Integer.parseInt(calc[0]) * Integer.parseInt(calc[2]);
            testRestTemplate.getForObject(
                    String.format(
                            "http://localhost:%d/bus/sendResponseEvent/calc/%s/%s", port, originalEventId, result
                    ), String.class
            );
        }
    }

}
