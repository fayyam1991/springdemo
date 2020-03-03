package com.india.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.india.springdemo.model.DeathStats;
import com.india.springdemo.services.CoronaVirusDeathService;

@Controller
public class DeathsController {

	@Autowired
	CoronaVirusDeathService coronaVirusDeathStats;

	@GetMapping("/deaths")
	public String deathsDetails(Model model) {
		List<DeathStats> allStats = coronaVirusDeathStats.getDeathsStats();
		int totalNoOfDeathCases = allStats.stream().mapToInt(stats -> stats.getDeaths()).sum();
		model.addAttribute("totalNoOfDeathCases", totalNoOfDeathCases);
		model.addAttribute("deathStats", allStats);
		return "deaths";
	}

}
