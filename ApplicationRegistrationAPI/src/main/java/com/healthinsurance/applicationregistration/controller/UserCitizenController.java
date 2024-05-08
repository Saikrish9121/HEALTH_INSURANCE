package com.healthinsurance.applicationregistration.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthinsurance.applicationregistration.bindings.SsaWebRequest;
import com.healthinsurance.applicationregistration.entity.Application;
import com.healthinsurance.applicationregistration.repo.UserCitizenRepository;
import com.healthinsurance.applicationregistration.service.UserCitizenService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/appregistration")
public class UserCitizenController {
	public static final Logger LOGGER=LoggerFactory.getLogger(UserCitizenController.class);
    @Autowired
    private UserCitizenService userService;
	
    @Autowired
    private UserCitizenRepository userRepo;
    
    @PostMapping("/eligibility")
    public Application getUserDetails(@RequestBody SsaWebRequest stateBinding) {
    	LOGGER.info("Inside the getUserDetails method in UserCitizenController class");
        Application savedUserPartial = userService.saveUserPartial(stateBinding);
        return savedUserPartial;
    }
	
    @PostMapping(path="/app", consumes="application/json")
    public ResponseEntity<Boolean> saveApplication(@RequestBody Application application) {
    	LOGGER.info("Inside the saveApplication method in UserCitizenController class");
        boolean isSaved = userService.saveApplication(application);
        return new ResponseEntity<>(isSaved, HttpStatus.CREATED);
    }

    @GetMapping(path="/app", produces="application/json")
    public ResponseEntity<List<Application>> viewAll() {
    	LOGGER.info("Inside the viewAll method in UserCitizenController class");
        List<Application> applications = userService.viewAll();
        return new ResponseEntity<>(applications, HttpStatus.OK);
    }

    @GetMapping(path="/app/{applicationNumber}", produces="application/json")
    public ResponseEntity<Application> findByApplicationNumber(@PathVariable Long applicationNumber) {
    	LOGGER.info("Inside the findByApplicationNumber method in UserCitizenController class");
        Application application = userService.findByApplicationNumber(applicationNumber);
        return new ResponseEntity<>(application, (application != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
    
    @GetMapping(path="/pendingcount")
    public Integer getPendingCount() {
    	LOGGER.info("Inside the getPendingCount method in UserCitizenController class");
    	return userRepo.countByAppStatusPending();
    	
    }
    
    @GetMapping(path="/pending", produces="application/json")
    public ResponseEntity<List<Application>> viewAllPending() {
    	LOGGER.info("Inside the viewAllPending method in UserCitizenController class");
        List<Application> applications = userService.viewAllPendingRequests();
        return new ResponseEntity<>(applications, HttpStatus.OK);
    }
    
    @PutMapping("/app")
    public Application changeStatus(Application app) {
    	LOGGER.info("Inside the changeStatus method in UserCitizenController class");
		return userService.changeStatus(app);
    }
    
    @PutMapping("/updateapp/{applicationId}")
    public ResponseEntity<Application> updateApplication(@PathVariable Long applicationId,
                                                         @RequestBody Application updatedApplication) {
    	LOGGER.info("Inside the updateApplication method in UserCitizenController class");
        Application existingApplication = userService.getApplicationById(applicationId);

        if (existingApplication == null) {
            return ResponseEntity.notFound().build();
        }
        existingApplication.setUserName(updatedApplication.getUserName());
        existingApplication.setDob(updatedApplication.getDob());
        existingApplication.setStateName(updatedApplication.getStateName());
        existingApplication.setMobileNumber(updatedApplication.getMobileNumber());
        existingApplication.setUserSSN(updatedApplication.getUserSSN());
        existingApplication.setUserSalary(updatedApplication.getUserSalary());
        existingApplication.setPlanId(updatedApplication.getPlanId());
        existingApplication.setUserId(updatedApplication.getUserId());
        existingApplication.setAppStatus(updatedApplication.getAppStatus());

        Application savedApplication = userService.saveApp(existingApplication);

        return ResponseEntity.ok(savedApplication);
    }
    
    
    @PutMapping("/updatestatus/{applicationId}")
    public ResponseEntity<Application> updateApplication(@PathVariable Long applicationId) {
    	LOGGER.info("Inside the updateApplication method in UserCitizenController class");
        Application existingApplication = userService.getApplicationById(applicationId);

        if (existingApplication == null) {
            return ResponseEntity.notFound().build();
        }
        existingApplication.setAppStatus("resolved");

        Application savedApplication = userService.saveApp(existingApplication);

        return ResponseEntity.ok(savedApplication);
    }
    
    
}
