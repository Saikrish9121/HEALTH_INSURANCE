package com.healthinsurance.applicationregistration.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.healthinsurance.applicationregistration.bindings.SsaWebRequest;
import com.healthinsurance.applicationregistration.bindings.SsaWebResponse;
import com.healthinsurance.applicationregistration.client.SsaClient;
import com.healthinsurance.applicationregistration.entity.Application;
import com.healthinsurance.applicationregistration.repo.UserCitizenRepository;

public class UserCitizenServiceImpTest {

    @InjectMocks
    private UserCitizenServiceImp service;

    @Mock
    private UserCitizenRepository userRepo;

    @Mock
    private SsaClient ssaClient;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveUserPartial() {
        SsaWebRequest request = new SsaWebRequest();
        request.setName("John");
        request.setSsn((long) 123456789);
        request.setDob("01-01-2000");

        SsaWebResponse response = new SsaWebResponse();
        response.setStateName("Rhode Island");

        when(ssaClient.checkEligibility(request)).thenReturn(response);

        Application application = service.saveUserPartial(request);

        assertEquals("John", application.getUserName());
        assertEquals((long)123456789, application.getUserSSN());
        assertEquals("01-01-2000", application.getDob());
        assertEquals("Rhode Island", application.getStateName());
    }

    @Test
    public void testSaveApplication() {
        Application application = new Application();
        application.setApplicationNumber((long) 1);

        when(userRepo.save(application)).thenReturn(application);

        boolean isSaved = service.saveApplication(application);

        assertTrue(isSaved);
    }

    @Test
    public void testViewAll() {
        Application application1 = new Application();
        Application application2 = new Application();

        when(userRepo.findAll()).thenReturn(Arrays.asList(application1, application2));

        List<Application> applications = service.viewAll();

        assertEquals(2, applications.size());
    }

//    @Test
//    public void testFindByApplicationNumber() {
//        Application application = new Application();
//        application.setApplicationNumber((long) 1);
//
//        when(userRepo.existsById(1)).thenReturn(true);
//        when(userRepo.findById(1)).thenReturn(Optional.of(application));
//
//        Application foundApplication = service.findByApplicationNumber(1);
//
//        assertEquals(1, foundApplication.getApplicationNumber());
//    }
}
