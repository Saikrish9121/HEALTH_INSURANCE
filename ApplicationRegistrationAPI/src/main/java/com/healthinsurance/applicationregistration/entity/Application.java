package com.healthinsurance.applicationregistration.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "applications")
public class Application {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long applicationNumber;
	private String userName;
	private String dob;
	private String stateName;
	private String mobileNumber;
	private Long userSSN;
	private Double userSalary;
	private Integer planId;
	private Integer userId;
	private String appStatus="pending";
	
	

}
