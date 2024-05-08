package com.healthinsurance.admin.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.healthinsurance.admin.model.PlanApprovalStatus;

@Repository
public interface PlanApprovalStatusRepo extends JpaRepository<PlanApprovalStatus, Integer> {
	
	
	 @Query("SELECT COUNT(p) FROM PlanApprovalStatus p WHERE p.approvalStatus = 'approved'")
	 public Integer countApprovedPlans();
	 
	 @Query("SELECT COUNT(p) FROM PlanApprovalStatus p WHERE p.userId = ?1")
	 public Integer countByUserId(Integer userId);
	 
	 @Query("SELECT COUNT(p) FROM PlanApprovalStatus p WHERE p.userId = ?1 AND p.approvalStatus = 'approved'")
	 public Integer countApprovedByUserId(Integer userId);
	 
	 @Query("SELECT COUNT(p) FROM PlanApprovalStatus p WHERE p.userId = ?1 AND p.approvalStatus = 'Denied'")
	 public Integer countDeniedByUserId(Integer userId);
	
}
