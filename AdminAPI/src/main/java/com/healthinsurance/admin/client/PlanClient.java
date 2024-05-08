package com.healthinsurance.admin.client;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "PlansManagement", url = "http://localhost:8081")
public interface PlanClient {
    @GetMapping("/api/plans/count")
    public Integer getActivePlanCount();
}
