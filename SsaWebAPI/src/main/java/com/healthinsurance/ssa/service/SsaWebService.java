package com.healthinsurance.ssa.service;

import com.healthinsurance.ssa.binding.SsaWebRequest;
import com.healthinsurance.ssa.binding.SsaWebResponse;

public interface SsaWebService {
	
	public SsaWebResponse getCitizenInfo(SsaWebRequest request);

}
