package com.healthinsurance.admin.binding;


import lombok.Data;

@Data
public class Application {
    private Long applicationNumber;
    private String userName;
    private String dob;
    private String stateName;
    private String mobileNumber;
    private Long userSSN;
    private Double userSalary;
    private Integer planId;
    private Integer userId;
    private Address address;
    private String appStatus;

   
}

