package com.assignment.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assignment.models.Application;

@Repository
public interface ApplicationRepository extends CrudRepository<Application,String>{
	
	public List<Application> findByOfferJobTitle(String jobTitle);
	

}
