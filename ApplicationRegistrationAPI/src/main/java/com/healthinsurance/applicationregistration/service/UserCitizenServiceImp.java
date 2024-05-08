package com.healthinsurance.applicationregistration.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthinsurance.applicationregistration.bindings.SsaWebRequest;
import com.healthinsurance.applicationregistration.bindings.SsaWebResponse;
import com.healthinsurance.applicationregistration.client.SsaClient;
import com.healthinsurance.applicationregistration.entity.Application;
import com.healthinsurance.applicationregistration.repo.UserCitizenRepository;

@Service
public class UserCitizenServiceImp implements UserCitizenService {
	public static final Logger LOGGER=LoggerFactory.getLogger(UserCitizenServiceImp.class);

    @Autowired
    private UserCitizenRepository userRepo;

    @Autowired
    private SsaClient ssaClient;

    @Override
    public Application saveUserPartial(SsaWebRequest ssaWebReq) {
    	LOGGER.info("Inside the saveUserPartial method in UserCitizenServiceImp class");
    	SsaWebResponse responseBody = ssaClient.checkEligibility(ssaWebReq);

        Application application = new Application();
        if (responseBody.getStateName().equalsIgnoreCase("Rhode Island")) {
            application.setUserName(ssaWebReq.getName());
            application.setUserSSN(ssaWebReq.getSsn());
            application.setDob(ssaWebReq.getDob());
            application.setStateName(responseBody.getStateName());
            userRepo.save(application);
        }
        return application;
    }


    @Override
    public boolean saveApplication(Application application) {
    	LOGGER.info("Inside the saveApplication method in UserCitizenServiceImp class");
        Application savedApplication = userRepo.save(application);
        return savedApplication.getApplicationNumber() != null;
    }

    @Override
    public List<Application> viewAll() {
    	LOGGER.info("Inside the viewAll method in UserCitizenServiceImp class");
        return userRepo.findAll();
    }

    @Override
    public Application findByApplicationNumber(Long applicationNumber) {
    	LOGGER.info("Inside the findByApplicationNumber method in UserCitizenServiceImp class");
        return userRepo.existsById(applicationNumber) ? userRepo.findById(applicationNumber).orElse(null) : null;
    }
    
    public List<Application> viewAllPendingRequests(){
    	LOGGER.info("Inside the viewAllPendingRequests method in UserCitizenServiceImp class");
    	return userRepo.findByAppStatus("pending");
    }
    
    @Override
    public Application changeStatus(Application app) {
    	LOGGER.info("Inside the changeStatus method in UserCitizenServiceImp class");
    	app.setAppStatus("resolved");
    	return userRepo.save(app);
    }


	@Override
	public Application updateApp(Long id, Application app) {
		LOGGER.info("Inside the updateApp method in UserCitizenServiceImp class");
		 Application existingapp = userRepo.findById(id).orElseThrow(null);
		 
	         existingapp.setApplicationNumber(app.getApplicationNumber());
	         existingapp.setUserName(app.getUserName());
	         existingapp.setDob(app.getDob());
	         existingapp.setStateName(app.getStateName());
	         existingapp.setMobileNumber(app.getMobileNumber());
	         existingapp.setUserSSN(app.getUserSSN());
	         existingapp.setUserSalary(app.getUserSalary());
	         existingapp.setPlanId(app.getPlanId());
	         existingapp.setUserId(app.getUserId());

	         return userRepo.save(existingapp);

	     } 
	
	
	@Override
    public Application getApplicationById(Long applicationId) {
		LOGGER.info("Inside the getApplicationById method in UserCitizenServiceImp class");
        return userRepo.findById(applicationId).orElse(null);
    }

    @Override
    public Application saveApp(Application application) {
    	LOGGER.info("Inside the saveApp method in UserCitizenServiceImp class");
        return userRepo.save(application);
    }
		
	

     
}
