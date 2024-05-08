package com.healthinsurance.plansmanagement.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthinsurance.plansmanagement.exception.PlanNotFoundException;
import com.healthinsurance.plansmanagement.model.Plan;
import com.healthinsurance.plansmanagement.repository.PlanRepository;
import com.healthinsurance.plansmanagement.service.PlanServiceImpl;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/plans")
public class PlanController {
	public static final Logger LOGGER=LoggerFactory.getLogger(PlanController.class);
    @Autowired
    private PlanServiceImpl planService;
    
    @Autowired
    private PlanRepository planRepo;

    @GetMapping
    public ResponseEntity<List<Plan>> getAllPlans() {
    	LOGGER.info("Inside the getAllPlans method in PlanController class");
        List<Plan> plans = planService.getAllPlans();
        return new ResponseEntity<>(plans, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getPlanById(@PathVariable("id") Long planId) {
    	LOGGER.info("Inside the getPlanById method in PlanController class");
        Plan plan = planService.getPlanById(planId);
        if(plan!=null) {
        	return new ResponseEntity<>(plan, HttpStatus.OK);
        }
        else {
        	return new ResponseEntity<String>("Plan not Found",HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Plan> addPlan(@RequestBody Plan plan) {
    	LOGGER.info("Inside the addPlan method in PlanController class");
        Plan addedPlan = planService.addPlan(plan);
        return new ResponseEntity<>(addedPlan, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plan> updatePlan(@PathVariable("id") Long planId, @RequestBody Plan planDetails) {
    	LOGGER.info("Inside the updatePlan method in PlanController class");
        Plan updatedPlan = planService.updatePlan(planId, planDetails);

        return new ResponseEntity<>(updatedPlan, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlan(@PathVariable("id") Long planId) throws PlanNotFoundException {
    	LOGGER.info("Inside the deletePlan method in PlanController class");
        planService.deletePlan(planId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    
    @GetMapping("/count")
    public Integer activePlansCount() {
    	LOGGER.info("Inside the activePlansCount method in PlanController class");
    	return planRepo.countActivePlans();
    	
    }

}
