package com.healthinsurance.citizenapi.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="AdminAPI", url="http://localhost:8084")
//@FeignClient("ADMINAPI")

public interface AdminClient {
	
	@GetMapping("/api/admin/plancount/{userId}")
	Integer getPlanCountByUserId(@PathVariable Integer userId);
	
	@GetMapping("/api/admin/approvedcount/{userId}")
	Integer getApprovedCountByUserId(@PathVariable Integer userId);
	
	@GetMapping("/api/admin/deniedcount/{userId}")
	Integer getDeniedCountByUserId(@PathVariable Integer userId);
}


