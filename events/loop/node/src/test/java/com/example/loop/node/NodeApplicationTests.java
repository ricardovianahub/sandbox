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
	void happyPath(String request, String expected) throws InterruptedException {
        System.out.printf("targetURL=[%s] - request [%s] - expected [%s]%n", targetURL, request, expected);

        String clientEventId = testRestTemplate.getForObject(
                targetURL + "/bus/sendRequestEvent/calc/" + request, String.class
        );

        String result;

        do {
            Thread.sleep(600);
            result = testRestTemplate.getForObject(
                    targetURL + "/bus/popNextResponseEvent/calc", String.class
            );
        } while ("[empty]".equals(result));
        assertEquals(expected, result);

        String emptyResult = testRestTemplate.getForObject(
                targetURL + "/bus/popNextResponseEvent/calc", String.class
        );
        assertEquals("[empty]", emptyResult);
	}

}
