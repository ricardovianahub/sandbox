package com.aa.improvekataben.repository;

import com.aa.improvekataben.data.ImprovementGrid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Component
public class ImprovementGridRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int insert(String teamName, String title, String field1Awesome, String field2Now, String field3Next, String field4Breakdown) {
        System.out.println("==> INSERTING for team [" + teamName + "] - title " + title);
        return jdbcTemplate.update(
                "INSERT INTO IMPROVEMENT_GRID (unique_id, team_name,title,field1_awesome,field2_now,field3_next,field4_breakdown) VALUES (?,?,?,?,?,?,?)",
                UUID.randomUUID().toString(), teamName, title, field1Awesome, field2Now, field3Next, field4Breakdown);
    }

    public List<ImprovementGrid> queryAll() {
        return jdbcTemplate.query("select * from IMPROVEMENT_GRID;", (resultSet, rowNum) -> new ImprovementGrid()
                .setUniqueId(resultSet.getString("unique_id"))
                .setCreatedAt(resultSet.getString("created_at"))
                .setTeamName(resultSet.getString("team_name"))
                .setTitle(resultSet.getString("title"))
                .setField1Awesome(resultSet.getString("field1_awesome"))
                .setField2Now(resultSet.getString("field2_now"))
                .setField3Next(resultSet.getString("field3_next"))
                .setField4Breakdown(resultSet.getString("field4_breakdown"))
        );
    }

    public int insert(ImprovementGrid improvementGrid) {
        return insert(
                improvementGrid.getTeamName(), improvementGrid.getTitle(), improvementGrid.getField1Awesome(),
                improvementGrid.getField2Now(), improvementGrid.getField3Next(), improvementGrid.getField4Breakdown()
        );
    }

    public void deleteByTeam(String teamName) {
        System.out.println("==> DELETING team [" + teamName + "]");
        jdbcTemplate.update("DELETE FROM IMPROVEMENT_GRID WHERE team_name = ?", teamName);
    }

    public List<ImprovementGrid> queryByTeamName(String teamName) {
        return jdbcTemplate.query("select * from IMPROVEMENT_GRID where team_name = '" + teamName + "'",
                (resultSet, rowNum) -> new ImprovementGrid()
                        .setUniqueId(resultSet.getString("unique_id"))
                        .setCreatedAt(resultSet.getString("created_at"))
                        .setTeamName(resultSet.getString("team_name"))
                        .setTitle(resultSet.getString("title"))
                        .setField1Awesome(resultSet.getString("field1_awesome"))
                        .setField2Now(resultSet.getString("field2_now"))
                        .setField3Next(resultSet.getString("field3_next"))
                        .setField4Breakdown(resultSet.getString("field4_breakdown"))
        );
    }

    public List<ImprovementGrid> queryByUniqueId(String uniqueId) {
        return jdbcTemplate.query("select * from IMPROVEMENT_GRID where unique_id = '" + uniqueId + "'",
                (resultSet, rowNum) -> new ImprovementGrid()
                        .setUniqueId(resultSet.getString("unique_id"))
                        .setCreatedAt(resultSet.getString("created_at"))
                        .setTeamName(resultSet.getString("team_name"))
                        .setTitle(resultSet.getString("title"))
                        .setField1Awesome(resultSet.getString("field1_awesome"))
                        .setField2Now(resultSet.getString("field2_now"))
                        .setField3Next(resultSet.getString("field3_next"))
                        .setField4Breakdown(resultSet.getString("field4_breakdown"))
        );
    }
}
