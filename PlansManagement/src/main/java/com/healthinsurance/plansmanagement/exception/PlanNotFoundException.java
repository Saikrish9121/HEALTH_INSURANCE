package com.healthinsurance.plansmanagement.exception;

@SuppressWarnings("serial")
public class PlanNotFoundException extends Exception{
	
	public PlanNotFoundException(String msg) {
		super(msg);
	}

}
