package com.healthinsurance.admin.binding;

import lombok.Data;

@Data
public class AdminDashBoardResponse {
	
	private long noOfUsers;
	private Integer noOfPendingRequests;
	private Integer noOfActivePlans;
	private Integer noOfPlansApproved;

}
