 package com.healthinsurance.citizenapi.bindings;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class DashBoardResponse {
	
	private Integer NoOfUsers;
	private Integer NoOfPlans;
	private Integer NoOfApproved;
	private Integer NoOfDenied;
	


}
