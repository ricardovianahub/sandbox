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

import com.aa.improvekataben.data.ImprovementGrid;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ImproveKataE2EApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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
        insertRecord(randomTitle);
        String s = testRestTemplate.getForObject("http://localhost:8200/queryAll", String.class);
        assertEquals("[{\"teamName\":\"inserted team name\",\"title\":\"" +
                        randomTitle +
                        "\",\"field1Awesome\":\"field1\",\"field2Now\":\"field2\",\"field3Next\":\"field3\",\"field4Breakdown\":\"field4\"}]"
                , s);
    }

    private void insertRecord(String randomTitle) {
        ImprovementGrid improvementGrid = new ImprovementGrid()
                .setTeamName("inserted team name")
                .setTitle(randomTitle)
                .setField1Awesome("field1")
                .setField2Now("field2")
                .setField3Next("field3")
                .setField4Breakdown("field4");
        testRestTemplate.postForObject("http://localhost:8200/insert", improvementGrid, ImprovementGrid.class);
    }

}
