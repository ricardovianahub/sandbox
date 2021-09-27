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
        String response = testRestTemplate.getForObject(String.format("http://localhost:%d/heartbeat", port), String.class);
        assertEquals("alive", response);
    }

    @ParameterizedTest
    @CsvSource({
            "3!x!4,12",
            "5!x!6,30"
    })
    void sendEventsInSequence(String request1, String expected1) {
        String clientEventId = testRestTemplate.getForObject(
                String.format("http://localhost:%d/sendRequestEvent/calc/" + request1, port), String.class
        );

        processOtherNode();

        String result = testRestTemplate.getForObject(
                String.format("http://localhost:%d/popResponseEvent/" + clientEventId, port), String.class
        );
        assertEquals(expected1, result);

        String emptyResult = testRestTemplate.getForObject(
                String.format("http://localhost:%d/popResponseEvent/" + clientEventId, port), String.class
        );
        assertEquals("[empty]", emptyResult);
    }

    @ParameterizedTest
    @CsvSource({
            "3!x!4,12,5!x!6,30"
    })
    void sendEventsOutOfSequence(String request1, String expected1, String request2, String expected2) {
        String clientEventId1 = sendEvent(request1, "http://localhost:%d/sendRequestEvent/calc/");
        String clientEventId2 = sendEvent(request2, "http://localhost:%d/sendRequestEvent/calc/");

        processOtherNode();
        processOtherNode();

        String result2 = sendEvent(clientEventId2, "http://localhost:%d/popResponseEvent/");
        assertEquals(expected2, result2);
        String result1 = sendEvent(clientEventId1, "http://localhost:%d/popResponseEvent/");
        assertEquals(expected1, result1);

        String emptyResult1 = sendEvent(clientEventId1, "http://localhost:%d/popResponseEvent/");
        assertEquals("[empty]", emptyResult1);
        String emptyResult2 = sendEvent(clientEventId2, "http://localhost:%d/popResponseEvent/");
        assertEquals("[empty]", emptyResult2);
    }

    private String sendEvent(String request, String s) {
        return testRestTemplate.getForObject(
                String.format(s + request, port), String.class
        );
    }

    private void processOtherNode() {
        String payload = testRestTemplate.getForObject(String.format("http://localhost:%d/popNextRequestEvent/calc", port), String.class);
        String[] request = payload.split(",");
        String originalEventId = request[0];
        String eventContent = request[1];
        String[] calc = eventContent.split("!");
        int result;
        if (calc[1].equals("x")) {
            result = Integer.parseInt(calc[0]) * Integer.parseInt(calc[2]);
            testRestTemplate.getForObject(
                    String.format(
                            "http://localhost:%d/sendResponseEvent/%s/%s", port, originalEventId, result
                    ), String.class
            );
        }
    }

}
