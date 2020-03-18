package com.india.springdemo.jaxrs.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.india.springdemo.model.LocationStats;
import com.india.springdemo.services.CoronaVirusDataService;

/**
 * 
 * @author Fayyam
 *
 */

@RestController
@RequestMapping("/api")
public class DetailsServiceResources {

	@Autowired
	private CoronaVirusDataService service;

	@RequestMapping(value = "deatils", method = RequestMethod.GET, produces = { "application/json" })
	public List<LocationStats> getDetailInfo() {
		return service.getAllStats();
	}

}
