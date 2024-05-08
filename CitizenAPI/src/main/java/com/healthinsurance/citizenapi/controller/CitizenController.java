package com.healthinsurance.citizenapi.controller;


import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.healthinsurance.citizenapi.bindings.DashBoardResponse;
import com.healthinsurance.citizenapi.entity.Citizen;
import com.healthinsurance.citizenapi.repo.CitiRepo;
import com.healthinsurance.citizenapi.service.CitizenService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/citizen")
public class CitizenController {
	public static final Logger LOGGER=LoggerFactory.getLogger(CitizenController.class);

	@Autowired
	private CitizenService citiService;
	
	@Autowired
	private CitiRepo cityRepo;
	
	
	@PostMapping("/register")
	public String loginChek(@RequestBody Citizen c)
	{
		LOGGER.info("Inside the loginChek method in CitizenController class");
		boolean user = citiService.saveUser(c);
		if(user)
		{
			return "Success";
		}
		return "Failed";
	}
	
	@PostMapping("/login")
    public ResponseEntity<Citizen> authenticateUser(@RequestParam String username, @RequestParam String password) {
		LOGGER.info("Inside the authenticateUser method in CitizenController class");
    	String un = URLDecoder.decode(username, StandardCharsets.UTF_8);
		String pass = URLDecoder.decode(password, StandardCharsets.UTF_8);
		
        Citizen loginUser = citiService.loginUser(un, pass);
        if (loginUser != null) {
            return new ResponseEntity<>(loginUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
	
	
	@PostMapping("/recover")
	public String getPassword(@RequestBody Map<String, String> body) {
		LOGGER.info("Inside the getPassword method in CitizenController class");
	    String email = body.get("email");
		String sub="Reset Password";
		String text="This is ur password " +cityRepo.getMyPassword(email);
		
		boolean pass = citiService.recoverPass(email, sub, text);
		
		if(pass)
		{
			return "Email Sent Success";
		}
		
		return "Email sent Failed";
	}
	
	@GetMapping("/dashboard/{userId}")
	public DashBoardResponse getInfoma(@PathVariable Integer userId) {
		LOGGER.info("Inside the getInfoma method in CitizenController class");
	    DashBoardResponse dash = citiService.getDash(userId);
	    return dash;
	}
	
	@GetMapping("/usercount")
	public long getUserCount(){
		LOGGER.info("Inside the getUserCount method in CitizenController class");
		return  cityRepo.count();
		
	}
	
	
	
}
