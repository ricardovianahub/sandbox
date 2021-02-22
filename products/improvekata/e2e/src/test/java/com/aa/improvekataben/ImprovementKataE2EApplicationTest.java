package com.aa.improvekataben;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.UUID;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
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

    @BeforeAll
    void beforeAll() {
        testRestTemplate.delete("http://localhost/ben/deleteTeam/DOD_REACCOM");
    }

    @AfterAll
    void afterAll() {
        testRestTemplate.delete("http://localhost/ben/deleteTeam/DOD_REACCOM");
    }

    @Test
    void monitor() {
        String s = testRestTemplate.getForObject("http://localhost/ben/monitor", String.class);
        assertTrue(s.startsWith("ImproveKataBenApplication UP since "));
    }

    @Test
    void insertReturnsInsertedObject() {
        String randomTitle = UUID.randomUUID().toString();
        String insert = addAndRetrieveRecord(randomTitle);
        assertEquals("[{\"teamName\":\"DOD_REACCOM\",\"title\":\"" +
                        randomTitle +
                        "\",\"field1Awesome\":\"field1\",\"field2Now\":\"field2\",\"field3Next\":\"field3\",\"field4Breakdown\":\"field4\"}]"
                , insert);
    }

    @Test
    void deleteRecordsForTeam() {
        String randomTitle = UUID.randomUUID().toString();
        addAndRetrieveRecord(randomTitle);
        testRestTemplate.delete("http://localhost/ben/deleteTeam/DOD_REACCOM");
        String queryAll = testRestTemplate.getForObject("http://localhost/ben/queryAll", String.class);
        assertEquals("[]", queryAll);
    }

    private String addAndRetrieveRecord(String randomTitle) {
        insertRecord(randomTitle);
        String queryAll = testRestTemplate.getForObject("http://localhost/ben/queryAll", String.class);
        return queryAll;
    }

    private void insertRecord(String randomTitle) {
        ImprovementGrid improvementGrid = new ImprovementGrid()
                .setTeamName("DOD_REACCOM")
                .setTitle(randomTitle)
                .setField1Awesome("field1")
                .setField2Now("field2")
                .setField3Next("field3")
                .setField4Breakdown("field4");
        testRestTemplate.postForObject("http://localhost/ben/insert", improvementGrid, ImprovementGrid.class);
    }

}
