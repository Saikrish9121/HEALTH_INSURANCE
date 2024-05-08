 package com.healthinsurance.citizenapi.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.healthinsurance.citizenapi.bindings.DashBoardResponse;
import com.healthinsurance.citizenapi.client.AdminClient;
import com.healthinsurance.citizenapi.entity.Citizen;
import com.healthinsurance.citizenapi.repo.CitiRepo;

import jakarta.mail.internet.MimeMessage;
@Service
public class CitizenServiceImp implements CitizenService {

	public static final Logger LOGGER=LoggerFactory.getLogger(CitizenServiceImp.class);
	@Autowired
	private CitiRepo cityRepo;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private AdminClient adminClient;
	
	
	@Override
	public boolean saveUser(Citizen c) {
		LOGGER.info("Inside the saveUser method in CitizenServiceImp class");
		Citizen save = cityRepo.save(c);
		if(save.getCid()!=null)
		{
			return true;
		}
		return false;
	}

	
        
	@Override
    public Citizen loginUser(String username, String password) {
		LOGGER.info("Inside the loginUser method in CitizenServiceImp class");
		return cityRepo.findByCmailAndPassword(username, password);
    }

	@Override
	public boolean recoverPass(String to, String sub, String text) {
		LOGGER.info("Inside the recoverPass method in CitizenServiceImp class");
		boolean result=false;
		try {
			
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message,true);
			helper.setSubject(sub);
			helper.setText(text);
			helper.setTo (to);
			mailSender.send(message);
			result=true;
			
		}catch (Exception e) {
			e.printStackTrace();
			result=false;
		}
		return result;
	}

	@Override
	public DashBoardResponse getDash(Integer userId) {
		LOGGER.info("Inside the DashBoardResponse method in CitizenServiceImp class");
//		Integer userId = (Integer) httpSession.getAttribute("userId");
		
		List<Citizen> findAll = cityRepo.findAll();
		DashBoardResponse d=new DashBoardResponse();
		d.setNoOfUsers(findAll.size());
		d.setNoOfPlans(adminClient.getPlanCountByUserId(userId));
		d.setNoOfApproved(adminClient.getApprovedCountByUserId(userId));					
		d.setNoOfDenied(adminClient.getDeniedCountByUserId(userId));
		return d;
//		adminClient.getPlanCountByUserId(userId)
//		adminClient.getApprovedCountByUserId(userId)
//		adminClient.getDeniedCountByUserId(userId)
	}

	


	

}
