package com.healthinsurance.ssa.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.healthinsurance.ssa.binding.SsaWebRequest;
import com.healthinsurance.ssa.binding.SsaWebResponse;

@Service
public class SsaWebServiceImpl implements SsaWebService{

	public static final Logger LOGGER=LoggerFactory.getLogger(SsaWebServiceImpl.class);
	@Override
	public SsaWebResponse getCitizenInfo(SsaWebRequest request) {
		LOGGER.info("Inside the getCitizenInfo method in SsaWebServiceImpl class");
		SsaWebResponse response=new SsaWebResponse();
		Long ssn = request.getSsn();
		String ssnStr = String.valueOf(ssn);
		response.setSsn(ssn);
		
		if(ssnStr.startsWith("1")) {
			response.setStateName("Texas");
		}else if(ssnStr.startsWith("2")) {
			response.setStateName("Missouri");
		}else if(ssnStr.startsWith("3")) {
			response.setStateName("Iowa");
		}else if(ssnStr.startsWith("4")) {
			response.setStateName("Rhode Island");
		}else if(ssnStr.startsWith("5")) {
			response.setStateName("Minnesota");
		}else if(ssnStr.startsWith("6")) {
			response.setStateName("Illinois");
		}else if(ssnStr.startsWith("7")) {
			response.setStateName("Milwaukee");
		}else if(ssnStr.startsWith("8")) {
			response.setStateName("Indiana");
		}else if(ssnStr.startsWith("9")) {
			response.setStateName("Ohio");
		}
		return response;
	}


}
