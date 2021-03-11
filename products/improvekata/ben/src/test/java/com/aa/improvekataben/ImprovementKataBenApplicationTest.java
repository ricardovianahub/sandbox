package com.aa.improvekataben;

import com.aa.improvekataben.data.ImprovementGrid;
import com.aa.improvekataben.repository.ImprovementGridRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ImproveKataBenApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class ImprovementKataBenApplicationTest {

    private static final String testTeamName = "BACKEND_TEST";

    @LocalServerPort
    private int port;

    @Autowired
    private ImprovementGridRepository improvementGridRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    void beforeEach() {
        deleteBackendTestTeam();
    }

    @AfterEach
    void afterEach() {
        deleteBackendTestTeam();
    }

    private void deleteBackendTestTeam() {
        jdbcTemplate.update("truncate table IMPROVEMENT_GRID");
//        jdbcTemplate.update("delete from IMPROVEMENT_GRID where team_name = ?; ", testTeamName);
    }

    @Test
    void writeToDB() {
        String result = insertRecord(testTeamName);
        String patternUniqueIDTemplate = "[a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12}";
        Pattern patternUniqueID = Pattern.compile(patternUniqueIDTemplate);
        assertTrue(patternUniqueID.matcher(result).matches(), "Matches pattern");
    }

    @Test
    void readFromDB() {
        insertRecord(testTeamName);
        List<ImprovementGrid> improvementGrids = improvementGridRepository.queryAll();
        assertEquals(1, improvementGrids.size());
        assertEquals("Title", improvementGrids.get(0).getTitle());
    }

    @Test
    void readFromDBByTeamName() {
        insertRecord("Team Alpha");
        insertRecord("Trash");
        insertRecord("Team Gamma");
        insertRecord("Team Gamma");

        List<ImprovementGrid> improvementGridsTeamAlpha = improvementGridRepository.queryByTeamName("Team Alpha");
        List<ImprovementGrid> improvementGridsTeamGamma = improvementGridRepository.queryByTeamName("Team Gamma");

        assertEquals(1, improvementGridsTeamAlpha.size());
        assertEquals(2, improvementGridsTeamGamma.size());
    }

    @Test
    void monitor() {
        insertRecord(testTeamName);
        String s = testRestTemplate.getForObject(String.format("http://localhost:%d/ben/monitor", port), String.class);
        assertTrue(s.startsWith("ImproveKataBenApplication UP since"));
    }

    private String insertRecord(String teamName) {
        String title = "Title";
        String field1Awesome = "Awesome";
        String field2Now = "Now";
        String field3Next = "Next";
        String field4Breakdown = "Breakdown";
        return improvementGridRepository.insert(
                teamName, title, field1Awesome, field2Now, field3Next, field4Breakdown
        );
    }

}
