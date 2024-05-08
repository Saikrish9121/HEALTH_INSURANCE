package com.healthinsurance.admin.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthinsurance.admin.model.Admin;

public interface AdminRepo extends JpaRepository<Admin, Integer> {

	public  Admin findByAdminEmailAndAdminPassword(String adminEmail, String adminPassword);
	

}
