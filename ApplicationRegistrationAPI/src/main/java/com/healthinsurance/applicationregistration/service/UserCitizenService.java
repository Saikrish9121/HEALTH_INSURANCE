package com.healthinsurance.applicationregistration.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.healthinsurance.applicationregistration.bindings.SsaWebRequest;
import com.healthinsurance.applicationregistration.entity.Application;

@Service
public interface UserCitizenService {
	
	public Application saveUserPartial(SsaWebRequest ssaWebReq);
	
	public boolean saveApplication(Application application);
	
	public List<Application> viewAll();
	
	public Application findByApplicationNumber(Long applicationNumber);
	
	public List<Application> viewAllPendingRequests();
	
	public Application changeStatus(Application app);

	public Application updateApp(Long id, Application app);
	
	public Application getApplicationById(Long applicationId);
	
	public Application saveApp(Application application);
}
