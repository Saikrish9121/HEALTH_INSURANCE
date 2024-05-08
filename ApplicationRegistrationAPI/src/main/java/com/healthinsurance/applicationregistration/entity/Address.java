package com.healthinsurance.applicationregistration.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Address {
	
	@Id
	private Integer addId;
	private String addType;
	private String city;
	private String zipcode;
	
	

}
	