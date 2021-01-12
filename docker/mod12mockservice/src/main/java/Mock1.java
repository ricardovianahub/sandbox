import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;

public class Mock1 {

    public static void main(String[] args) throws InterruptedException {
        ClientAndServer clientAndServer = startClientAndServer(8112);
        new MockServerClient("localhost", 8112)
                .when(
                        request()
                                .withMethod("GET")
                                .withPath("/test2")
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withCookie(
                                        "sessionId", "2By8LOhBmaW5nZXJwcmludCIlMDAzMW"
                                )
                                .withHeader(
                                        "Location", "https://www.mock-server.com"
                                )
                                .withBody("[{\"firstName\":\"Alice\",\"lastName\":\"Doe\",\"age\":21,\"salutation\":\"Mrs\",\"carrier\":\"AA\"},{\"firstName\":\"John\",\"lastName\":\"Doe\",\"age\":30,\"salutation\":\"Mr\",\"carrier\":\"AA\"},{\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"age\":28,\"salutation\":\"Miss\",\"carrier\":\"AA\"},{\"firstName\":\"Alan\",\"lastName\":\"Smithee\",\"age\":25,\"salutation\":\"N/A\",\"carrier\":\"AA\"},{\"firstName\":\"Bob\",\"lastName\":\"Smithee\",\"age\":24,\"salutation\":\"N/A\",\"carrier\":\"AA\"},{\"firstName\":\"Joe\",\"lastName\":\"Smithee\",\"age\":25,\"salutation\":\"N/A\",\"carrier\":\"AA\"},{\"firstName\":\"Joe\",\"lastName\":\"Schmo\",\"age\":22,\"salutation\":\"Mr\",\"carrier\":\"AA\"},{\"firstName\":\"Julia\",\"lastName\":\"Schmo\",\"age\":26,\"salutation\":\"Miss\",\"carrier\":\"AA\"},{\"firstName\":\"Bob\",\"lastName\":\"Slob\",\"age\":27,\"salutation\":\"Mr\",\"carrier\":\"AA\"},{\"firstName\":\"NEW\",\"lastName\":\"NEW\",\"age\":99,\"salutation\":\"N/A\",\"carrier\":\"AA\"},{\"firstName\":\"No\",\"lastName\":\"Way\",\"age\":44,\"salutation\":\"N/A\",\"carrier\":\"AA\"},{\"firstName\":\"Sergio\",\"lastName\":\"David\",\"age\":24,\"salutation\":\"N/A\",\"carrier\":\"AA\"},{\"firstName\":\"Ricardo\",\"lastName\":\"Test04\",\"age\":16,\"salutation\":\"Mr\",\"carrier\":\"AA\"}]")
                );
        Thread.sleep(60000);
        clientAndServer.stop();
    }

}
