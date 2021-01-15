package app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = LatlongApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LatlongTest {

    private ClientAndServer clientAndServer;
    private ObjectMapper objectMapper;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private LatlongEndpoint latlongEndpoint;

    @BeforeAll
    void beforeAll() {
        objectMapper = new ObjectMapper();
        if (latlongEndpoint.isLocal()) {
            clientAndServer = startClientAndServer(8113);
            new MockServerClient("localhost", 8113)
                    .when(
                            request()
                                    .withMethod("GET")
                                    .withPath("/points")
                    )
                    .respond(
                            response()
                                    .withStatusCode(200)
                                    .withBody("{" +
                                            "   \"properties\":{" +
                                            "     \"relativeLocation\":{" +
                                            "       \"properties\": {" +
                                            "         \"city\": \"Allen\"," +
                                            "         \"state\": \"TX\"" +
                                            "         }" +
                                            "      }" +
                                            "    }" +
                                            "}")
                                    .withHeader("Content-type", "application/json")
                    );
        }
    }

    @Test
    void startUp() {
    }

    @Test
    void defaultLatlongResponse() throws JsonProcessingException {
        System.out.println("========================================");
        System.out.println("========================================");
        System.out.println("ENDPOINT: " + latlongEndpoint.url());
        System.out.println("========================================");
        System.out.println("========================================");
        String result;
        CityState2 cityState2;
        Map<String, Map<String, Map<String, Map<String, String>>>> map;

        result = testRestTemplate.getForObject(latlongEndpoint.url(), String.class);
        map = objectMapper.readValue(result, Map.class);
        assertEquals("Allen", map.get("properties").get("relativeLocation").get("properties").get("city"));
        assertEquals("TX", map.get("properties").get("relativeLocation").get("properties").get("state"));

        cityState2 = testRestTemplate.getForObject(latlongEndpoint.url(), CityState2.class);
        assertEquals("Allen", cityState2.getProperties().getRelativeLocation().getProperties().getCity());
        assertEquals("TX", cityState2.getProperties().getRelativeLocation().getProperties().getState());
    }

    @AfterAll
    void afterAll() {
        if (latlongEndpoint.isLocal()) {
            clientAndServer.stop();
        }
    }


}
