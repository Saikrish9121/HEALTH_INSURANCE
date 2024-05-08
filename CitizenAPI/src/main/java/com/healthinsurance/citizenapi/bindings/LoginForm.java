package com.healthinsurance.citizenapi.bindings;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class LoginForm {
	
	private String email;
	private String password;
	
}
