package com.aa.improvekataben;

import com.aa.improvekataben.data.ImprovementGrid;
import com.aa.improvekataben.repository.ImprovementGridRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping(path = "/")
public class ImproveKataBenApplication {

	@Autowired
	private ImprovementGridRepository improvementGridRepository;

	public static void main(String[] args) {
		SpringApplication.run(ImproveKataBenApplication.class, args);
	}

	@GetMapping(path = "/queryAll", produces="application/json")
	public List<ImprovementGrid> queryAll() {
		return improvementGridRepository.queryAll();
	}

	@PostMapping(path = "/insert", consumes = "application/json")
	public void insert(@RequestBody ImprovementGrid improvementGrid) {
		improvementGridRepository.insert(improvementGrid);
	}

}
