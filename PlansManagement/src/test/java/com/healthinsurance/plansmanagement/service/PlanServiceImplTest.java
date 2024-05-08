package com.healthinsurance.plansmanagement.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.healthinsurance.plansmanagement.exception.PlanNotFoundException;
import com.healthinsurance.plansmanagement.model.Plan;
import com.healthinsurance.plansmanagement.repository.PlanRepository;

public class PlanServiceImplTest {

    @InjectMocks
    private PlanServiceImpl planService;

    @Mock
    private PlanRepository planRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllPlans() {
        List<Plan> plans = Arrays.asList(
                new Plan(1L, "Plan A",  new Date(),new Date(),"Active"),
                new Plan(2L, "Plan B",  new Date(),new Date(),"Active")
        );
        when(planRepository.findAll()).thenReturn(plans);
        List<Plan> result = planService.getAllPlans();
        assertEquals(2, result.size());
        assertEquals("Plan A", result.get(0).getPlanName());
        assertEquals("Plan B", result.get(1).getPlanName());
    }

    @Test
    public void testAddPlan() {
        Plan newPlan = new Plan(1L, "Plan A",  new Date(),new Date(),"Active");
        when(planRepository.save(newPlan)).thenReturn(new Plan(1L, "Plan A",  new Date(),new Date(),"Active"));
        Plan savedPlan = planService.addPlan(newPlan);
        assertNotNull(savedPlan.getPlanId());
        assertEquals("Plan A", savedPlan.getPlanName());
        assertEquals("Active", savedPlan.getPlanStatus());
    }

    @Test
    public void testUpdatePlan() {
        Plan existingPlan = new Plan(1L, "Plan A",  new Date(),new Date(),"Inactive");
        Plan updatedPlan = new Plan(1L, "Plan A",  new Date(),new Date(),"Active");
        when(planRepository.findById(1L)).thenReturn(Optional.of(existingPlan));
        when(planRepository.save(existingPlan)).thenReturn(updatedPlan);
        Plan result = planService.updatePlan(1L, updatedPlan);
        assertEquals("Plan A", result.getPlanName());
        assertEquals("Active", result.getPlanStatus());
    }

    @Test
    public void testDeletePlan() {
        when(planRepository.findById(1L)).thenReturn(Optional.of(new Plan(1L, "Plan A",  new Date(),new Date(),"Inactive")));
        assertDoesNotThrow(() -> planService.deletePlan(1L));
    }

    @Test
    public void testDeletePlan_NotFound() {
        when(planRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(PlanNotFoundException.class, () -> planService.deletePlan(1L));
    }

    @Test
    public void testGetPlanById() {
        Plan existingPlan = new Plan(1L, "Plan A",  new Date(),new Date(),"Active");
        when(planRepository.existsById(1L)).thenReturn(true);
        when(planRepository.findById(1L)).thenReturn(Optional.of(existingPlan));
        Plan result = planService.getPlanById(1L);
        assertEquals("Plan A", result.getPlanName());
        assertEquals("Active", result.getPlanStatus());
    }

    @Test
    public void testGetPlanById_NotFound() {
        when(planRepository.existsById(1L)).thenReturn(false);
        Plan result = planService.getPlanById(1L);
        assertNull(result);
    }
}

