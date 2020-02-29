package com.india.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.india.springdemo.model.LocationStats;
import com.india.springdemo.services.CoronaVirusDataService;

@Controller
public class homeController {

	@Autowired
	CoronaVirusDataService coronaVirusService;

	@GetMapping("/")
	public String home(Model model) {
		List<LocationStats> allStats = coronaVirusService.getAllStats();
		int totalNoOfCases = allStats.stream().mapToInt(stats -> stats.getLatestReportedCases()).sum();
		model.addAttribute("allStats", allStats);
		model.addAttribute("totalNoOfCases", totalNoOfCases);
		return "home";
	}


}
