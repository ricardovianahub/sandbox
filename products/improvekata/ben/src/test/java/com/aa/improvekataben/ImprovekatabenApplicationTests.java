package com.aa.improvekataben;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ImprovekatabenApplicationTests {

    @Autowired
    private ImprovementGridRepository improvementGridRepository;

    @Test
    void writeToDB() {
        String teamName = "Team name";
        String title = "Title";
        String field1Awesome = "Awesome";
        String field2Now = "Now";
        String field3Next = "Next";
        String field4Breakdown = "Breakdown BLA BLA BLA";
        int result = improvementGridRepository.insert(
                teamName, title, field1Awesome, field2Now, field3Next, field4Breakdown
        );
        assertEquals(result, 1);
    }

}
