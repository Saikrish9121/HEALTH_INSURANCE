package com.healthinsurance.ssa.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.healthinsurance.ssa.binding.SsaWebRequest;
import com.healthinsurance.ssa.binding.SsaWebResponse;

public class SsaWebServiceImplTest {

    @InjectMocks
    private SsaWebServiceImpl ssaWebService;

    

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCitizenInfo_Texas() {
        SsaWebRequest request = new SsaWebRequest();
        request.setSsn(123456789L);
        SsaWebResponse response = ssaWebService.getCitizenInfo(request);
        assertEquals("Texas", response.getStateName());
    }
    
    @Test
    public void testGetCitizenInfo_Missouri() {
        SsaWebRequest request = new SsaWebRequest();
        request.setSsn(201234567L);
        SsaWebResponse response = ssaWebService.getCitizenInfo(request);
        assertEquals("Missouri", response.getStateName());
    }
    
    @Test
    public void testGetCitizenInfo_Iowa() {
        SsaWebRequest request = new SsaWebRequest();
        request.setSsn(301234567L);
        SsaWebResponse response = ssaWebService.getCitizenInfo(request);
        assertEquals("Iowa", response.getStateName());
    }

    @Test
    public void testGetCitizenInfo_RhodeIsland() {
        SsaWebRequest request = new SsaWebRequest();
        request.setSsn(401234567L);
        SsaWebResponse response = ssaWebService.getCitizenInfo(request);
        assertEquals("Rhode Island", response.getStateName());
    }

  

    @Test
    public void testGetCitizenInfo_Minnesota() {
        SsaWebRequest request = new SsaWebRequest();
        request.setSsn(501234567L);
        SsaWebResponse response = ssaWebService.getCitizenInfo(request);
        assertEquals("Minnesota", response.getStateName());
    }

    @Test
    public void testGetCitizenInfo_Illinois() {
        SsaWebRequest request = new SsaWebRequest();
        request.setSsn(601234567L);
        SsaWebResponse response = ssaWebService.getCitizenInfo(request);
        assertEquals("Illinois", response.getStateName());
    }

    @Test
    public void testGetCitizenInfo_Milwaukee() {
        SsaWebRequest request = new SsaWebRequest();
        request.setSsn(701234567L);
        SsaWebResponse response = ssaWebService.getCitizenInfo(request);
        assertEquals("Milwaukee", response.getStateName());
    }

    @Test
    public void testGetCitizenInfo_Indiana() {
        SsaWebRequest request = new SsaWebRequest();
        request.setSsn(801234567L);
        SsaWebResponse response = ssaWebService.getCitizenInfo(request);
        assertEquals("Indiana", response.getStateName());
    }


    @Test
    public void testGetCitizenInfo_Ohio() {
        SsaWebRequest request = new SsaWebRequest();
        request.setSsn(901234567L);
        SsaWebResponse response = ssaWebService.getCitizenInfo(request);
        assertEquals("Ohio", response.getStateName());
    }
}
