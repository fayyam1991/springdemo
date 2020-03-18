package com.india.springdemo.jaxrs.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.india.springdemo.model.DeathStats;
import com.india.springdemo.services.CoronaVirusDeathService;

/**
 * 
 * @author Fayyam
 *
 */
@RestController
@RequestMapping("/api")
public class DeathServiceResuorce {

	@Autowired
	private CoronaVirusDeathService service;

	@RequestMapping(value = "/death", method = RequestMethod.GET, produces = { "application/json" })
	public List<DeathStats> getDetailInfo() {
		return service.getDeathsStats();
	}
	
}
