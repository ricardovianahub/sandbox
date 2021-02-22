package com.aa.improvekataben;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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

    @BeforeEach
    void beforeEach() {
        testRestTemplate.delete("http://localhost/ben/deleteTeam/TEST");
    }

    @AfterEach
    void afterEach() {
        testRestTemplate.delete("http://localhost/ben/deleteTeam/TEST");
    }

    @Test
    void monitor() {
        String s = testRestTemplate.getForObject("http://localhost/ben/monitor", String.class);
        assertTrue(s.startsWith("ImproveKataBenApplication UP since "));
    }

    @Test
    void insertReturnsInsertedObject() {
        String randomTitle = UUID.randomUUID().toString();
        String insert = addAndRetrieveRecord("TEST", randomTitle);
        assertEquals("[{\"teamName\":\"TEST\",\"title\":\"" +
                        randomTitle +
                        "\",\"field1Awesome\":\"field1\",\"field2Now\":\"field2\",\"field3Next\":\"field3\",\"field4Breakdown\":\"field4\"}]"
                , insert);
    }

    @Test
    void deleteRecordsForTeam() {
        String randomTitle = UUID.randomUUID().toString();
        addAndRetrieveRecord("TEST", randomTitle);
        addAndRetrieveRecord("NOISE", randomTitle);
        testRestTemplate.delete("http://localhost/ben/deleteTeam/TEST");
        String queryByTeamName = testRestTemplate.getForObject("http://localhost/ben/queryByTeamName/TEST", String.class);
        assertEquals("[]", queryByTeamName);
    }

    private String addAndRetrieveRecord(String teamName, String randomTitle) {
        insertRecord(teamName, randomTitle);
        String queryAll = testRestTemplate.getForObject(String.format("http://localhost/ben/queryByTeamName/%s", teamName), String.class);
        return queryAll;
    }

    private void insertRecord(String teamName, String randomTitle) {
        ImprovementGrid improvementGrid = new ImprovementGrid()
                .setTeamName(teamName)
                .setTitle(randomTitle)
                .setField1Awesome("field1")
                .setField2Now("field2")
                .setField3Next("field3")
                .setField4Breakdown("field4");
        testRestTemplate.postForObject("http://localhost/ben/insert", improvementGrid, ImprovementGrid.class);
    }

}
