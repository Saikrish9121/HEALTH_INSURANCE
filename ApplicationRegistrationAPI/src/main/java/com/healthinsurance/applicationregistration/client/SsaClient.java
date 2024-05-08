package com.healthinsurance.applicationregistration.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.healthinsurance.applicationregistration.bindings.SsaWebRequest;
import com.healthinsurance.applicationregistration.bindings.SsaWebResponse;

@FeignClient(name = "SsaWebAPI", url = "http://localhost:8090")
public interface SsaClient {
    @PostMapping("/ssa")
    SsaWebResponse checkEligibility(@RequestBody SsaWebRequest ssaWebRequest);
}

