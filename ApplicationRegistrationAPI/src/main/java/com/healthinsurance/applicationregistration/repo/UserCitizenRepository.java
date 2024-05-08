package com.healthinsurance.applicationregistration.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.healthinsurance.applicationregistration.entity.Application;

@Repository
public interface UserCitizenRepository extends JpaRepository<Application, Long>{

	
	List<Application> findByAppStatus(String status);
	
	@Query("SELECT COUNT(a) FROM Application a WHERE a.appStatus = 'pending'")
    Integer countByAppStatusPending();
}
