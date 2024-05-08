package com.healthinsurance.admin.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthinsurance.admin.binding.AdminDashBoardResponse;
import com.healthinsurance.admin.binding.Application;
import com.healthinsurance.admin.client.ApplicationRegistrationClient;
import com.healthinsurance.admin.client.CitizenClient;
import com.healthinsurance.admin.client.PlanClient;
import com.healthinsurance.admin.model.Admin;
import com.healthinsurance.admin.model.PlanApprovalStatus;
import com.healthinsurance.admin.repo.AdminRepo;
import com.healthinsurance.admin.repo.PlanApprovalStatusRepo;

@Service
public class AdminServiceImpl implements AdminService {
	
	public static final Logger LOGGER=LoggerFactory.getLogger(AdminServiceImpl.class);
	
	@Autowired
	private AdminRepo adminRepo;
	
	@Autowired
	private PlanApprovalStatusRepo planStatusRepo;
	
	@Autowired
	private CitizenClient citizenClient;
	
	@Autowired
	private PlanClient planClient;
	
	@Autowired
	private ApplicationRegistrationClient appRegClient;


	@Override
    public Admin loginUser(String username, String password) {
		LOGGER.info("Inside the loginUser method in AdminServiceImpl class");
        return adminRepo.findByAdminEmailAndAdminPassword(username, password);
	}
	@Override
	public AdminDashBoardResponse getDash() {
		LOGGER.info("Inside the AdminDashBoardResponse method in AdminServiceImpl class");
		Integer countApprovedPlans = planStatusRepo.countApprovedPlans();
		AdminDashBoardResponse d=new AdminDashBoardResponse();
		d.setNoOfUsers(citizenClient.getUserCount());
		d.setNoOfActivePlans(planClient.getActivePlanCount());
		d.setNoOfPendingRequests(appRegClient.getPendingCount());
		d.setNoOfPlansApproved(countApprovedPlans);
		return d;
	}

	@Override
	public boolean ApprovePlan(PlanApprovalStatus plan) {
		LOGGER.info("Inside the ApprovePlan method in AdminServiceImpl class");
		PlanApprovalStatus save = planStatusRepo.save(plan);
		if(save!=null) {
			return true;
		}
		return false;
	}

	@Override
	public List<Application> viewAllApplications() {
		LOGGER.info("Inside the viewAllApplications method in AdminServiceImpl class");
		return appRegClient.viewAllApplications();
	}
	
	public List<Admin> viewAllAdmins(){
		LOGGER.info("Inside the viewAllAdmins method in AdminServiceImpl class");
		return adminRepo.findAll();
	}

	@Override
	public Admin saveAdmin(Admin admin) {
		LOGGER.info("Inside the saveAdmin method in AdminServiceImpl class");
		return adminRepo.save(admin);
	}
	@Override
	public PlanApprovalStatus getApplicationById(Integer planId) {
		LOGGER.info("Inside the getApplicationById method in AdminServiceImpl class");
		return planStatusRepo.findById(planId).orElseThrow();
	}
	@Override
	public PlanApprovalStatus saveApp(PlanApprovalStatus existingPlanApp) {
		LOGGER.info("Inside the saveApp method in AdminServiceImpl class");
		return planStatusRepo.save(existingPlanApp);
	}

}
