package com.healthinsurance.plansmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.healthinsurance.plansmanagement.model.Plan;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
	
	
	@Query("SELECT COUNT(p) FROM Plan p WHERE p.planStatus = 'Active'")
    Integer countActivePlans();
	
}