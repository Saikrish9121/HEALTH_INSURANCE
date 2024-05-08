package com.healthinsurance.citizenapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.healthinsurance.citizenapi.entity.Citizen;

public interface CitiRepo extends JpaRepository<Citizen, Integer>{
	
	public Citizen findByCmailAndPassword(String email,String pass);
	
	@Query("SELECT password FROM Citizen WHERE cmail = :email")
	public String getMyPassword(String email);

}
