package com.example.loop.node;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(classes = NodeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NodeApplicationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Value("${targetURL}")
    private String targetURL;

    @ParameterizedTest
    @CsvSource({
            "3!x!4,12",
            "5!x!6,30"
    })
	void happyPath(String request1, String expected1) throws InterruptedException {
        System.out.println(String.format("targetURL=[%s] - request [%s] - expected [%s]", targetURL, request1, expected1));

        String clientEventId = testRestTemplate.getForObject(
                String.format(targetURL + "/sendRequestEvent/calc/" + request1), String.class
        );

        String result;

        do {
            Thread.sleep(600);
            result = testRestTemplate.getForObject(
                    String.format(targetURL + "/popResponseEvent/" + clientEventId), String.class
            );
        } while ("[empty]".equals(result));
        assertEquals(expected1, result);

        String emptyResult = testRestTemplate.getForObject(
                String.format(targetURL + "/popResponseEvent/" + clientEventId), String.class
        );
        assertEquals("[empty]", emptyResult);
	}

}
