package com.aa.vthreads;

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
    public String delay() {
        ResponseEntity<Void> result = restClient.get()
                .retrieve()
                .toEntity(Void.class);

        log.info("{} on {}", result.getStatusCode(), Thread.currentThread());

        return Thread.currentThread().toString();
    }

}
