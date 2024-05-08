package com.healthinsurance.admin.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "CitizenAPI", url = "http://localhost:8083")
public interface CitizenClient {
    @GetMapping("/api/citizen/usercount")
    public Integer getUserCount();
}
