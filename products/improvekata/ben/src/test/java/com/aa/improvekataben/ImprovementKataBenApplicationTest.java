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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ImproveKataBenApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ImprovementKataBenApplicationTest {

    @Autowired
    private ImprovementGridRepository improvementGridRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    void beforeEach() {
        jdbcTemplate.update("truncate table IMPROVEMENT_GRID; ");
    }

    @AfterEach
    void afterEach() {
        jdbcTemplate.update("truncate table IMPROVEMENT_GRID; ");
    }

    @Test
    void writeToDB() {
        int result = insertRecord();
        assertEquals(result, 1);
    }

    @Test
    void readFromDB() {
        insertRecord();
        List<ImprovementGrid> improvementGrids = improvementGridRepository.queryAll();
        assertEquals(1, improvementGrids.size());
        assertEquals("Title", improvementGrids.get(0).getTitle());
    }

    @Test
    void queryAll() {
        insertRecord();
        String s = testRestTemplate.getForObject("http://localhost:8200/queryAll", String.class);
        assertEquals("[{\"teamName\":\"Team name\",\"title\":\"Title\",\"field1Awesome\":\"Awesome\",\"field2Now\":\"Now\",\"field3Next\":\"Next\",\"field4Breakdown\":\"Breakdown\"}]"
                , s);
    }

    @Test
    void insert() {
        ImprovementGrid improvementGrid = new ImprovementGrid()
                .setTeamName("inserted team name")
                .setTitle("inserted title")
                .setField1Awesome("field1")
                .setField2Now("field2")
                .setField3Next("field3")
                .setField4Breakdown("field4");
        testRestTemplate.postForObject("http://localhost:8200/insert", improvementGrid, ImprovementGrid.class);
        String s = testRestTemplate.getForObject("http://localhost:8200/queryAll", String.class);
        assertEquals("[{\"teamName\":\"inserted team name\",\"title\":\"inserted title\",\"field1Awesome\":\"field1\",\"field2Now\":\"field2\",\"field3Next\":\"field3\",\"field4Breakdown\":\"field4\"}]"
                , s);
    }

    private int insertRecord() {
        String teamName = "Team name";
        String title = "Title";
        String field1Awesome = "Awesome";
        String field2Now = "Now";
        String field3Next = "Next";
        String field4Breakdown = "Breakdown";
        int result = improvementGridRepository.insert(
                teamName, title, field1Awesome, field2Now, field3Next, field4Breakdown
        );
        return result;
    }


}
