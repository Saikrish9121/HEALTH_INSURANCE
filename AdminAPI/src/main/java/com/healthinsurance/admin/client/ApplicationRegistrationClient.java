package com.healthinsurance.admin.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.healthinsurance.admin.binding.Application;

@FeignClient(name = "ApplicationRegistrationAPI", url = "http://localhost:8082")
public interface ApplicationRegistrationClient {
	
    @GetMapping("/api/appregistration/pendingcount")
    public Integer getPendingCount();
    
    
    @GetMapping("/api/appregistration/app")
    public List<Application> viewAllApplications();
}

