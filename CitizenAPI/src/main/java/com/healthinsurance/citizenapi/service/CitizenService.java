package com.healthinsurance.citizenapi.service;

import com.healthinsurance.citizenapi.bindings.DashBoardResponse;
import com.healthinsurance.citizenapi.entity.Citizen;

public interface CitizenService {
	
	public boolean saveUser(Citizen c);
    public DashBoardResponse getDash(Integer userId);
    public Citizen loginUser(String username, String password);
	public boolean recoverPass(String to,String sub,String text);
	
	

}
