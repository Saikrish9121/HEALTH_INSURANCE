package com.healthinsurance.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.healthinsurance.admin.binding.AdminDashBoardResponse;
import com.healthinsurance.admin.binding.Application;
import com.healthinsurance.admin.model.Admin;
import com.healthinsurance.admin.model.PlanApprovalStatus;

@Service
public interface AdminService {
	
	public Admin loginUser(String username, String password);
	
	public Admin saveAdmin(Admin admin);
	
	public AdminDashBoardResponse getDash();
	
	public boolean ApprovePlan(PlanApprovalStatus plan);
	
	public List<Application> viewAllApplications();
	
	public List<Admin> viewAllAdmins();

	public PlanApprovalStatus getApplicationById(Integer planId);

	public PlanApprovalStatus saveApp( PlanApprovalStatus existingPlanApp );
	
}
