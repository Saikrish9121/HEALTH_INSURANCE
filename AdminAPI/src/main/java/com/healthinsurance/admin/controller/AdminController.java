package com.healthinsurance.admin.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.healthinsurance.admin.binding.AdminDashBoardResponse;
import com.healthinsurance.admin.binding.Application;
import com.healthinsurance.admin.model.Admin;
import com.healthinsurance.admin.model.PlanApprovalStatus;
import com.healthinsurance.admin.repo.PlanApprovalStatusRepo;
import com.healthinsurance.admin.service.AdminService;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/admin")
public class AdminController {
	public static final Logger LOGGER=LoggerFactory.getLogger(AdminController.class);
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private PlanApprovalStatusRepo planRepo;
	
	
	
	@GetMapping("/register")
	public List<Admin> registerPage()
	{
		LOGGER.info("Inside the registerPage method in Admin Controller");
		return adminService.viewAllAdmins();
	}
	
	
	
	@PostMapping("/register")
	public Admin loginChek(@RequestBody Admin admin)
	{
		LOGGER.info("Inside the loginCheck method in Admin Controller");
		return adminService.saveAdmin(admin);
	}
	
	@PostMapping("/login")
    public ResponseEntity<Admin> authenticateAdmin(@RequestParam String username, @RequestParam String password) {
		LOGGER.info("Inside the authenticateAdmin method in Admin Controller");
    	String un = URLDecoder.decode(username, StandardCharsets.UTF_8);
		String pass = URLDecoder.decode(password, StandardCharsets.UTF_8);
		
        Admin admin = adminService.loginUser(un, pass);
        if (admin != null) {
            return new ResponseEntity<>(admin, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

	
	@GetMapping("/plancount/{id}")
	public ResponseEntity<Integer> getPlanCountByUserId(@PathVariable("id") Integer userId) {
		LOGGER.info("Inside the getPlanCountByUserId method in Admin Controller");
	    Integer count = planRepo.countByUserId(userId);
	    if (count != null) {
	        return ResponseEntity.ok(count);
	    } else {
	        // Assuming that a null count means there are no plans for the user
	        return ResponseEntity.notFound().build();
	    }
	}

	
	
	@GetMapping("/approvedcount/{id}")
	public Integer getApprovedCountByUserId(@PathVariable("id") Integer userId) {
		LOGGER.info("Inside the getApprovedCountByUserId method in Admin Controller");
		return planRepo.countApprovedByUserId(userId);
	}
	
	
	@GetMapping("/deniedcount/{id}")
	public Integer getDeniedCountByUserId(@PathVariable("id") Integer userId) {
		LOGGER.info("Inside the getDeniedCountByUserId method in Admin Controller");
		return planRepo.countDeniedByUserId(userId);
	}
	
	@GetMapping("/report")
	public void generateReport(HttpServletResponse response) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
	    
		LOGGER.info("Inside the generateReport method in Admin Controller");
	    String filename = "report.csv";

	    response.setContentType("text/csv");
	    response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
	        "attachment; filename=\"" + filename + "\"");

	    
	    StatefulBeanToCsv<Application> writer = new StatefulBeanToCsvBuilder<Application>(response.getWriter())
	        .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
	        .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
	        .withOrderedResults(false)
	        .build();

	    
	    writer.write(adminService.viewAllApplications());
	}

	@GetMapping("/admindash")
	public AdminDashBoardResponse getDashBoard() {
		LOGGER.info("Inside the getDashBoard method in Admin Controller");
		return adminService.getDash();
	}
	
	
	@PutMapping("/changeplanstatus/{id}")
	public String approvePlanByUserId(@PathVariable("id") Integer userId ,@RequestBody PlanApprovalStatus plan) {
		LOGGER.info("Inside the approvePlanByUserId method in Admin Controller");
		PlanApprovalStatus save = planRepo.save(plan);
		if(save.getApprovalStatus().equals("Approved")) {
			return "Plan Approved Successfully";
		}
		else if(save.getApprovalStatus().equals("Denied")) {
			return "Plan Denied";
		}
		return "plan is still in pending";
	}
	
	
	@PutMapping("/changeplan/{id}")
	public ResponseEntity<PlanApprovalStatus> updatePlan(@PathVariable("id") Integer planId ,@RequestBody PlanApprovalStatus plan) {
		LOGGER.info("Inside the updatePlan method in Admin Controller");
		 PlanApprovalStatus existingPlanApp = adminService.getApplicationById(planId);
		
		if (existingPlanApp == null) {
            return ResponseEntity.notFound().build();
        }
		existingPlanApp.setPlanName(plan.getPlanName());
		existingPlanApp.setBenefitAmount(plan.getBenefitAmount());
		existingPlanApp.setApprovedDate(plan.getApprovedDate());
		existingPlanApp.setExpiryDate(plan.getExpiryDate());
		existingPlanApp.setUserId(plan.getUserId());
		existingPlanApp.setApprovalStatus(plan.getApprovalStatus());
		adminService.saveApp(existingPlanApp);
		return ResponseEntity.ok(existingPlanApp);
	}

}
