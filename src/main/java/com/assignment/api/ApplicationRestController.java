package com.assignment.api;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.models.Application;
import com.assignment.models.Offer;
import com.assignment.service.ApplicationService;

@RestController

public class ApplicationRestController {
	
	@Autowired
	private ApplicationService applicationService;
	
	@RequestMapping("/offers/{jobTitle}/applications")
	public ResponseEntity<List<Application>> getAllApplications(@PathVariable String jobTitle)
	{
				List<Application> apps = new ArrayList<>();
				applicationService.getAllApplications(jobTitle).forEach(apps::add);
		        return new ResponseEntity<List<Application>>(apps,HttpStatus.OK);
	}
	
	@RequestMapping("/offers/{jobTitle}/applications/{email}")
	public ResponseEntity<Application> getApplication(@PathVariable String email)
	{ 
				Application app=applicationService.getApplication(email);
				return new ResponseEntity<Application>(app,HttpStatus.OK);
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value="/offers/{jobTitle}/applications") 
	public ResponseEntity<Application> addApplication(@Valid @RequestBody Application application,  @PathVariable String jobTitle) throws MethodArgumentNotValidException 
	{
		application.setOffer(new Offer(jobTitle));
		applicationService.addApplication(application,jobTitle);
		return new ResponseEntity<Application>(application,HttpStatus.OK);
	}
	

}
