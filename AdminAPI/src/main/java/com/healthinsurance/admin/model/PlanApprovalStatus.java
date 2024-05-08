package com.healthinsurance.admin.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class PlanApprovalStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer planId;
	private String planName;
	private Double benefitAmount;
	private Date approvedDate;
	private Date expiryDate;
	private Integer userId;
	private String approvalStatus;

}
