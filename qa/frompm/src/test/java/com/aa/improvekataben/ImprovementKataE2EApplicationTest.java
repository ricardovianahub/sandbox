package com.aa.improvekataben;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = FromPMApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ImprovementKataE2EApplicationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void monitor() {
        String s = testRestTemplate.getForObject("http://localhost:8200/monitor", String.class);
        assertTrue(s.startsWith("ImproveKataBenApplication UP since "));
    }

    @Test
    void addNewRecord() {
        String randomTitle = UUID.randomUUID().toString();
        String s = testRestTemplate.getForObject("http://localhost:8200/queryAll", String.class);
        assertEquals("[{\"teamName\":\"inserted team name\",\"title\":\"" +
                        randomTitle +
                        "\",\"field1Awesome\":\"field1\",\"field2Now\":\"field2\",\"field3Next\":\"field3\",\"field4Breakdown\":\"field4\"}]"
                , s);
    }

}
