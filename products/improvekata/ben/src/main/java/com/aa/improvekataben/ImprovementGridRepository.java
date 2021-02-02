package com.aa.improvekataben;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ImprovementGridRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int insert(String teamName, String title, String field1Awesome, String field2Now, String field3Next, String field4Breakdown) {
        int result = jdbcTemplate.update(
                "INSERT INTO IMPROVEMENT_GRID (team_name,title,field1_awesome,field2_now,field3_next,field4_breakdown) VALUES (?,?,?,?,?,?)",
                new Object[]{teamName, title, field1Awesome, field2Now, field3Next, field4Breakdown}
        );
        return result;
    }
}
