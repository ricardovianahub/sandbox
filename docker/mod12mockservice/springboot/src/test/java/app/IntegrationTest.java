package app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.mock.Expectation;
import org.mockserver.model.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Mod12.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IntegrationTest {

    private ClientAndServer clientAndServer;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeAll
    void beforeAll() {
        clientAndServer = startClientAndServer(8112);
        new MockServerClient("localhost", 8112)
                .when(
                        request()
                                .withMethod("GET")
                                .withPath("/person")
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withBody("{ \"firstName\": \"John\", \"lastName\": \"Doe\", \"age\": 20 }")
                                .withHeader("Content-type", "application/json")
                );
    }

    @Test
    void checkResponse200() {
        new MockServerClient("localhost", 8112)
                .when(
                        request()
                                .withMethod("GET")
                                .withPath("/check")
                                .withQueryStringParameters(Parameter.param("test"))
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withBody("checked")
                );

        ResponseEntity<String> response = testRestTemplate.getForEntity("http://localhost:8112/check?test", String.class);
        assertEquals("200 OK", response.getStatusCode().toString());
        assertEquals("checked", response.getBody());
    }

    @Test
    void checkResultDifferentValue() {
        new MockServerClient("localhost", 8112)
                .when(
                        request()
                                .withMethod("GET")
                                .withPath("/check")
                                .withQueryStringParameters(Parameter.param("something"))
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withBody("something else")
                );

        ResponseEntity<String> response = testRestTemplate.getForEntity("http://localhost:8112/check?something", String.class);
        assertEquals("200 OK", response.getStatusCode().toString());
        assertEquals("something else", response.getBody());
    }

    @Test
    void personResponseFitsExpectedFormat() {
        Person person = testRestTemplate.getForObject("http://localhost:8112/person", Person.class);
        assertEquals("John", person.getFirstName());
        assertEquals("Doe", person.getLastName());
        assertEquals(20, person.getAge().intValue());
    }

    @Test
    void nameResponseFitsExpectedFormat() {
        new MockServerClient("localhost", 8112)
                .when(
                        request()
                                .withMethod("GET")
                                .withPath("/name")
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withBody("{ \"first\": \"Alan\", \"last\": \"Jones\" }")
                                .withHeader("Content-type", "application/json")
                );
        Name name = testRestTemplate.getForObject("http://localhost:8112/name", Name.class);
        assertEquals("Alan", name.getFirst());
        assertEquals("Jones", name.getLast());
    }

    @AfterAll
    void afterAll() {
        clientAndServer.stop();
    }
}
