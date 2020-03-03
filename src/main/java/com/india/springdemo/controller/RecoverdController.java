package com.india.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.india.springdemo.model.RecoverdStats;
import com.india.springdemo.services.CoronaVirusRecoveredService;

@Controller
public class RecoverdController {
	
	@Autowired
	CoronaVirusRecoveredService coronaVirusRecoveredService;

	@GetMapping("/recoverd")
	public String recoveredDetail(Model model) {
		List<RecoverdStats> recoverdStats = coronaVirusRecoveredService.getRecoveredStats();
		int totalNoOfRecoveredCases = recoverdStats.stream().mapToInt(stats -> stats.getRecovered()).sum();
		model.addAttribute("recoveredStats", recoverdStats);
		model.addAttribute("totalNoOfRecoveredCases", totalNoOfRecoveredCases);
		return "recovered";
	}

}
