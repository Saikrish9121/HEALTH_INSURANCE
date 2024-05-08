 package com.healthinsurance.citizenapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;

import com.healthinsurance.citizenapi.bindings.DashBoardResponse;
import com.healthinsurance.citizenapi.bindings.LoginForm;
import com.healthinsurance.citizenapi.client.AdminClient;
import com.healthinsurance.citizenapi.entity.Citizen;
import com.healthinsurance.citizenapi.repo.CitiRepo;

import jakarta.mail.internet.MimeMessage;

public class CitizenServiceImpTest {

    @InjectMocks
    private CitizenServiceImp service;

    @Mock
    private CitiRepo cityRepo;

    @Mock
    private JavaMailSender mailSender;


    @Mock
    private AdminClient adminClient;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveUser() {
        Citizen citizen = new Citizen();
        citizen.setCid(1);

        when(cityRepo.save(citizen)).thenReturn(citizen);
        boolean isSaved = service.saveUser(citizen);
        assertTrue(isSaved);
    }

    @Test
    public void testLoginUser() {
        LoginForm loginForm = new LoginForm();
        loginForm.setEmail("test@test.com");
        loginForm.setPassword("password");

        Citizen citizen = new Citizen();
        citizen.setCid(1);
        citizen.setCmail("test@test.com");
        citizen.setPassword("password");

        when(cityRepo.findByCmailAndPassword(loginForm.getEmail(), loginForm.getPassword())).thenReturn(citizen);
        Citizen loggedInCitizen = service.loginUser(citizen.getCmail(), citizen.getPassword());
        assertEquals(citizen, loggedInCitizen);
    }

    @Test
    public void testRecoverPass() throws Exception {
        String to = "test@test.com";
        String sub = "Password Recovery";
        String text = "Your password has been recovered.";
        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
        
        boolean isRecovered = service.recoverPass(to, sub, text);
        assertTrue(isRecovered);
    }

    @Test
    public void testGetDash() {
        Citizen citizen1 = new Citizen();
        Citizen citizen2 = new Citizen();
        Integer userId=1;
        when(cityRepo.findAll()).thenReturn(Arrays.asList(citizen1, citizen2));
        when(adminClient.getPlanCountByUserId(1)).thenReturn(2);
        when(adminClient.getApprovedCountByUserId(1)).thenReturn(1);
        when(adminClient.getDeniedCountByUserId(1)).thenReturn(1);

        DashBoardResponse dashBoardResponse = service.getDash(userId);

        assertEquals(2, dashBoardResponse.getNoOfUsers());
        assertEquals(2, dashBoardResponse.getNoOfPlans());
        assertEquals(1, dashBoardResponse.getNoOfApproved());
        assertEquals(1, dashBoardResponse.getNoOfDenied());
    }
}
