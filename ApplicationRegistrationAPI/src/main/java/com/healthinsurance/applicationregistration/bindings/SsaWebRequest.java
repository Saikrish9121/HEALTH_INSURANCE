package com.healthinsurance.applicationregistration.bindings;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class SsaWebRequest {
	
	private String name;
	private String dob;
	private Long ssn;
	
}