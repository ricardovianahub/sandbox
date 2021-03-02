package com.aa.improvekataben;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.aa.TestUtils;
import com.aa.improvekataben.data.ImprovementGrid;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ImproveKataE2EApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ImprovementKataE2EApplicationTest {

    private ObjectMapper mapper = new ObjectMapper();
    private String baseURL;

    @BeforeAll
    void beforeAll() {
        baseURL = TestUtils.retrieveBaseURL();
    }

    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    void beforeEach() {
        testRestTemplate.delete(baseURL + "/ben/deleteTeam/TEST");
    }

    @AfterEach
    void afterEach() {
        testRestTemplate.delete(baseURL + "/ben/deleteTeam/TEST");
    }

    @Test
    void monitor() {
        String s = testRestTemplate.getForObject(baseURL + "/ben/monitor", String.class);
        assertTrue(s.startsWith("ImproveKataBenApplication UP since "));
    }

    @Test
    void insertReturnsInsertedObject() throws Exception {
        String randomTitle = UUID.randomUUID().toString();
        insertRecord("TEST", randomTitle);
        String queryByTeamName = testRestTemplate.getForObject(baseURL + "/ben/queryByTeamName/TEST", String.class);
        List<Map<String, Object>> data = mapper.readValue(queryByTeamName, new TypeReference<>() {
        });

        assertEquals("TEST", data.get(0).get("teamName"));
        assertEquals(randomTitle, data.get(0).get("title"));
        assertEquals("field1", data.get(0).get("field1Awesome"));
        assertEquals("field2", data.get(0).get("field2Now"));
        assertEquals("field3", data.get(0).get("field3Next"));
        assertEquals("field4", data.get(0).get("field4Breakdown"));
    }

    @Test
    void deleteRecordsForTeam() {
        String randomTitle = UUID.randomUUID().toString();
        addAndRetrieveRecord("TEST", randomTitle);
        addAndRetrieveRecord("NOISE", randomTitle);
        testRestTemplate.delete(baseURL + "/ben/deleteTeam/TEST");
        String queryByTeamName = testRestTemplate.getForObject(baseURL + "/ben/queryByTeamName/TEST", String.class);
        assertEquals("[]", queryByTeamName);
        testRestTemplate.delete(baseURL + "/ben/deleteTeam/NOISE");
    }

    private String addAndRetrieveRecord(String teamName, String randomTitle) {
        insertRecord(teamName, randomTitle);
        String queryAll = testRestTemplate.getForObject(String.format(baseURL + "/ben/queryByTeamName/%s", teamName), String.class);
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
        testRestTemplate.postForObject(baseURL + "/ben/insert", improvementGrid, ImprovementGrid.class);
    }

}
