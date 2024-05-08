package com.healthinsurance.plansmanagement.service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthinsurance.plansmanagement.exception.PlanNotFoundException;
import com.healthinsurance.plansmanagement.exception.ResourceNotFoundException;
import com.healthinsurance.plansmanagement.model.Plan;
import com.healthinsurance.plansmanagement.repository.PlanRepository;

@Service
public class PlanServiceImpl implements PlanService{
	
	public static final Logger LOGGER=LoggerFactory.getLogger(PlanServiceImpl.class);
	
    @Autowired
    private PlanRepository planRepository;

    public List<Plan> getAllPlans() {
    	LOGGER.info("Inside the getAllPlans method in PlanServiceImpl class");
        return planRepository.findAll();

    }

    public Plan addPlan(Plan plan) {
    	LOGGER.info("Inside the addPlan method in PlanServiceImpl class");
        plan.setCreatedDate(new Date());

        return planRepository.save(plan);

    }

    public Plan updatePlan(Long planId, Plan planDetails) {
    	LOGGER.info("Inside the updatePlan method in PlanServiceImpl class");
        Optional<Plan> optionalPlan = planRepository.findById(planId);

        if (optionalPlan.isPresent()) {

            Plan existingPlan = optionalPlan.get();

            existingPlan.setPlanName(planDetails.getPlanName());

            existingPlan.setUpdatedDate(new Date());	
 
            existingPlan.setPlanStatus(planDetails.getPlanStatus());

            return planRepository.save(existingPlan);

        } else {

            throw new ResourceNotFoundException("Plan not found with id: " + planId);

        }

    }

    public void deletePlan(Long planId) throws PlanNotFoundException{
    	LOGGER.info("Inside the deletePlan method in PlanServiceImpl class");
    	Optional<Plan> findById = planRepository.findById(planId);
    	if(findById.isPresent()) {
    		 planRepository.deleteById(planId);
    	}else {
    		throw new PlanNotFoundException("Plan Not found with Id:"+planId);
    	}
       

    }


	public Plan getPlanById(Long planId) {
		LOGGER.info("Inside the getPlanById method in PlanServiceImpl class");
		if(planRepository.existsById(planId)) {
			return planRepository.findById(planId).orElse(null);
		}
		else {
			return null;
		}
		
	}

}
