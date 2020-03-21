package com.india.springdemo.jaxrs.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.india.springdemo.model.IndiaStats;
import com.india.springdemo.services.CoronaVirusIndiaService;

/**
 * 
 * @author Fayyam
 *
 */

@RestController
@RequestMapping("/api")
public class IndiaInfoServiceResource {

	
	@Autowired
	private CoronaVirusIndiaService service;

	@RequestMapping(value = "/indiaInfo", method = RequestMethod.GET, produces = { "application/json" })
	public List<IndiaStats> getDetailInfo() {
		return service.getIndiaInfo();
	}
	
}
