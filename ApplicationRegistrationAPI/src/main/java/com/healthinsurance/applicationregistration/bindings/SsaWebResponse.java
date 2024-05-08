package com.healthinsurance.applicationregistration.bindings;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class SsaWebResponse {
	
	private Long ssn;
	private String stateName;
	

}
