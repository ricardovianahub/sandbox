package com.aa.vthreads;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/vthreads")
public class VthreadsController {

    private static final Logger log = LoggerFactory.getLogger(VthreadsController.class);
    private final RestClient restClient;

    public VthreadsController(RestClient.Builder restClientBuilder) {
        restClient = restClientBuilder.baseUrl("https://jsonplaceholder.typicode.com/users").build();
    }

    @GetMapping("/users")
    public String users() {
        ResponseEntity<String> result = restClient.get()
                .retrieve()
                .toEntity(String.class);

        log.info("{} on {}", result.getStatusCode(), Thread.currentThread());

        return result.getBody();
    }

    @GetMapping("/userCollection")
    public List<String> userCollection() {
        List<String> resultCollection = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();
        log.info("Top of the method: {}", Thread.currentThread());

        for (int i = 1; i <= 10; i++) {
            final int finalI = i;
            threads.add(
                    Thread.startVirtualThread(() -> {
                                log.info("In the loop: {}", Thread.currentThread());
                                String response = restClient.get()
                                        .uri("/" + finalI)
                                        .retrieve()
                                        .toEntity(String.class).getBody();
                                if (response.isEmpty()) {
                                    resultCollection.add("Error message");
                                } else {
                                    resultCollection.add(response);
                                }
                            }
                    )
            );
        }

        threads.forEach((t) -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        return resultCollection;
    }

}
