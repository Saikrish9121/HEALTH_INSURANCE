package com.healthinsurance.plansmanagement.service;

import java.util.List;

import com.healthinsurance.plansmanagement.exception.PlanNotFoundException;
import com.healthinsurance.plansmanagement.model.Plan;

public interface PlanService {

	public List<Plan> getAllPlans();
	
	public Plan addPlan(Plan plan);
	
	public Plan updatePlan(Long planId, Plan planDetails);
	
	public void deletePlan(Long planId) throws PlanNotFoundException;
	
	public Plan getPlanById(Long planId);
	
	

}
