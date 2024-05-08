package com.healthinsurance.admin.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.healthinsurance.admin.binding.AdminDashBoardResponse;
import com.healthinsurance.admin.binding.AdminLoginForm;
import com.healthinsurance.admin.binding.Application;
import com.healthinsurance.admin.client.ApplicationRegistrationClient;
import com.healthinsurance.admin.client.CitizenClient;
import com.healthinsurance.admin.client.PlanClient;
import com.healthinsurance.admin.model.Admin;
import com.healthinsurance.admin.model.PlanApprovalStatus;
import com.healthinsurance.admin.repo.AdminRepo;
import com.healthinsurance.admin.repo.PlanApprovalStatusRepo;

public class AdminServiceImplTest {

    @InjectMocks
    private AdminServiceImpl adminService;

    @Mock
    private AdminRepo adminRepo;

    @Mock
    private PlanApprovalStatusRepo planStatusRepo;

    @Mock
    private CitizenClient citizenClient;

    @Mock
    private PlanClient planClient;

    @Mock
    private ApplicationRegistrationClient appRegClient;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveAdmin() {
        Admin admin = new Admin();
        admin.setAdminId(1);
        when(adminRepo.save(any(Admin.class))).thenReturn(admin);

        Admin saveAdmin = adminService.saveAdmin(admin);
        assertEquals(saveAdmin,admin);
    }

    @Test
    public void testLoginUser() {
        Admin admin = new Admin();
        admin.setAdminId(1);
        AdminLoginForm loginForm = new AdminLoginForm();
        loginForm.setAdminEmail("test@test.com");
        loginForm.setAdminPassword("password");
        when(adminRepo.findByAdminEmailAndAdminPassword(anyString(), anyString())).thenReturn(admin);

        Admin result = adminService.loginUser(loginForm.getAdminEmail(), loginForm.getAdminPassword());
        assertEquals(admin, result);
    }

    @Test
    public void testGetDash() {
        when(citizenClient.getUserCount()).thenReturn((int) 10L);
        when(planClient.getActivePlanCount()).thenReturn(5);
        when(appRegClient.getPendingCount()).thenReturn(3);
        when(planStatusRepo.countApprovedPlans()).thenReturn(2);

        AdminDashBoardResponse result = adminService.getDash();
        assertEquals(10L, result.getNoOfUsers());
        assertEquals(5, result.getNoOfActivePlans());
        assertEquals(3, result.getNoOfPendingRequests());
        assertEquals(2, result.getNoOfPlansApproved());
    }

    @Test
    public void testApprovePlan() {
        PlanApprovalStatus plan = new PlanApprovalStatus();
        plan.setPlanId(1);
        when(planStatusRepo.save(any(PlanApprovalStatus.class))).thenReturn(plan);

        boolean result = adminService.ApprovePlan(plan);
        assertTrue(result);
    }

    @Test
    public void testViewAllApplications() {
        Application app1 = new Application();
        app1.setApplicationNumber(1L);
        Application app2 = new Application();
        app2.setApplicationNumber(2L);
        when(appRegClient.viewAllApplications()).thenReturn(Arrays.asList(app1, app2));

        List<Application> result = adminService.viewAllApplications();
        assertEquals(2, result.size());
    }

    @Test
    public void testViewAllAdmins() {
        Admin admin1 = new Admin();
        admin1.setAdminId(1);
        Admin admin2 = new Admin();
        admin2.setAdminId(2);
        when(adminRepo.findAll()).thenReturn(Arrays.asList(admin1, admin2));

        List<Admin> result = adminService.viewAllAdmins();
        assertEquals(2, result.size());
    }
}
