package com.aa.improvekataben;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aa.improvekataben.api.BenResponse;
import com.aa.improvekataben.data.ImprovementGrid;
import com.aa.improvekataben.repository.ImprovementGridRepository;

@SpringBootApplication
@RestController
@RequestMapping(path = "/ben")
public class ImproveKataBenApplication {

    private Instant startUpTime = Instant.now();

    @Autowired
    private ImprovementGridRepository improvementGridRepository;

    public static void main(String[] args) {
        SpringApplication.run(ImproveKataBenApplication.class, args);
    }

    @GetMapping(path = "/queryAll", produces = "application/json")
    public List<ImprovementGrid> queryAll() {
        return improvementGridRepository.queryAll();
    }

    @GetMapping(path = "/queryByTeamName/{teamName}", produces = "application/json")
    public List<ImprovementGrid> queryByTeamName(@PathVariable String teamName) {
        return improvementGridRepository.queryByTeamName(teamName);
    }

    @GetMapping(path = "/queryByUniqueId/{uniqueId}", produces = "application/json")
    public List<ImprovementGrid> queryByUniqueId(@PathVariable String uniqueId) {
        return improvementGridRepository.queryByUniqueId(uniqueId);
    }

    @DeleteMapping(path = "/deleteTeam/{teamName}")
    public void deleteTeam(@PathVariable String teamName) {
        improvementGridRepository.deleteByTeam(teamName);
    }

    @PostMapping(path = "/insert", consumes = "application/json")
    public BenResponse insert(@RequestBody ImprovementGrid improvementGrid) {
        return improvementGridRepository.insert(improvementGrid);
    }

    @GetMapping(path = "/monitor", produces = "text/plain")
    public String monitor() {
        return "ImproveKataBenApplication UP since " + startUpTime;
    }

}
