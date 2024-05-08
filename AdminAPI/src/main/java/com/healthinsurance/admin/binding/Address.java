package com.healthinsurance.admin.binding;

import lombok.Data;

@Data
public class Address {
    private Integer addId;
    private String addType;
    private String city;
    private String zipcode;

}