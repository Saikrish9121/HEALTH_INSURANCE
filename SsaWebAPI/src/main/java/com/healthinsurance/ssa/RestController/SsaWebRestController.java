package com.healthinsurance.ssa.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.healthinsurance.ssa.binding.SsaWebRequest;
import com.healthinsurance.ssa.binding.SsaWebResponse;
import com.healthinsurance.ssa.service.SsaWebService;


@RestController
public class SsaWebRestController {
	
	public static final Logger LOGGER=LoggerFactory.getLogger(SsaWebRestController.class);
	@Autowired
	private SsaWebService service;
	
	@PostMapping(path="/ssa",produces="application/json")
	public SsaWebResponse getCitizenInfo(@RequestBody SsaWebRequest req) {
		 LOGGER.info("Inside the getCitizenInfo method in SsaWebRestController class");
		 return service.getCitizenInfo(req);
	}
	
	

}
