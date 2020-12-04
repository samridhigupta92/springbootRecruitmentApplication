package com.assignment.service;

import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.assignment.exception.DataNotFoundException;
import com.assignment.models.Application;
import com.assignment.models.Offer;
import com.assignment.repository.*;

@Service
public class ApplicationService { 
	
	@Autowired
	private ApplicationRepository applicationRepository;
	
	@Autowired
	private OfferRepository offerRepository ;
	
	
	public List<Application> getAllApplications(String jobTitle) throws DataNotFoundException
	{				
		List<Application> applications = new ArrayList<>();
		applicationRepository.findByOfferJobTitle(jobTitle).forEach(applications::add);
		if(applications.size()==0) {
			throw new DataNotFoundException("No applications available");
		}
		return applications;

	}
	
	public Application getApplication(String email) throws DataNotFoundException
	{		
		
		//return applications.stream().filter(t-> t.getJobTitle().equals(jobTitle)).findFirst().get();	
		Application app= applicationRepository.findById(email).orElse(null);
		if(app==null)		
			throw new DataNotFoundException("Application with email account "+email+" not found");
		
		return app;
	}
	
	public void addApplication(Application application, String jobTitle)
	{
		application.setOffer(new Offer(jobTitle));
		applicationRepository.save(application);		
		Integer applicants=(int) applicationRepository.findByOfferJobTitle(jobTitle).stream().count();		
		offerRepository.updateOffer(jobTitle,applicants);
		
	}
	

	
		
	}

