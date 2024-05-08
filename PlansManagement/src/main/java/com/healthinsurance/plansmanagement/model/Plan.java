package com.healthinsurance.plansmanagement.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "plan_master_tbl")
public class Plan {


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_id")
    private Long planId;

    @Column(name = "plan_name")
    private String planName;

    @Column(name = "created_date",updatable = false)
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;
    
    @Column(name="plan_status")
    private String planStatus;
    

   
}
