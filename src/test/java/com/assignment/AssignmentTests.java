package com.assignment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.assignment.models.Application;
import com.assignment.models.Offer;
import com.assignment.repository.ApplicationRepository;
import com.assignment.repository.OfferRepository;

@DataJpaTest
public class AssignmentTests {
	
	@Autowired
	private OfferRepository offerRepository;
	
	@Autowired
	private ApplicationRepository applicationRepository;
	
	public void addOffer(String jobTitle)
	{
		Offer offer = new Offer(jobTitle,Calendar.getInstance().getTime(),0);
		offerRepository.save(offer);
	}
	
	public void add(String email, String resumeText, String appStatus, String jobTitle)
	{
		Application application = new Application(jobTitle,email,resumeText,appStatus);
		applicationRepository.save(application);
	}
	@Test
	public void testAddOffer() throws ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Offer offer = new Offer("SDE1",sdf.parse("2021-01-01"),0);		
		Offer savedOffer = offerRepository.save(offer);
		 Offer getFromDb = offerRepository.findById(savedOffer.getJobTitle()).orElse(null);
		 assertNotNull(getFromDb);
		assertNotNull(savedOffer);
		assertThat(savedOffer).isEqualTo(getFromDb);
		
		
		
	}
	@Test
	public void testGetOfferByJobTitle()
	{
		addOffer("SDE1");
		String jobTitle="SDE1";
		Offer offer=offerRepository.findById(jobTitle).orElse(null);
		System.out.println(offer.getJobTitle());
		assertNotNull(offer);
		
		assertThat(offer.getJobTitle()).isEqualTo(jobTitle);	
		
	}
	@Test
	public void testNotGetOfferByJobTitle()
	{
		addOffer("SDE1");
		String jobTitle="SDE2";
		Offer offer=offerRepository.findById(jobTitle).orElse(null);
		assertNull(offer);	
					
	}
	
	@Test
	public void testGetOffers()
	{
		addOffer("SDE1");
		addOffer("SDE2");
		addOffer("SDE3");
		List<Offer> offers = new ArrayList<>();
		offerRepository.findAll().forEach(offers::add);
		assertNotNull(offers);	
		assertThat(offers.size()).isEqualTo(offerRepository.count());
		
					
	}
	
	@Test
	public void addApplication()
	{
		addOffer("SDE1");
		Application app =new Application("SDE1", "abc@gmail.com","Applying for SDE1","APPLIED");
		Application savedApp =applicationRepository.save(app);
		Application getAppDb= applicationRepository.findById("abc@gmail.com").orElse(null);
		assertNotNull(savedApp);
		assertNotNull(getAppDb);
		assertThat(savedApp).isEqualTo(getAppDb);				
	}
	
	@Test
	public void testGetApplicationByEmail()
	{
		addOffer("SDE1");
		addOffer("SDE2");
		
		add("abc@gmail.com","Applying for SDE1","APPLIED","SDE1");
		add("xyz@gmail.com","Applying for SDE1","APPLIED","SDE1");
		add("ab@gmail.com","Applying for SDE2","APPLIED","SDE2");
		add("xy@gmail.com","Applying for SDE2","APPLIED","SDE2");
		
		String email="abc@gmail.com";
		
		Application app = applicationRepository.findById(email).orElse(null);
		
		assertNotNull(app);
		assertThat(app.getEmail()).isEqualTo(email);
		
		
	}
	
	@Test
	public void testGetAppsByOffers()
	{
		addOffer("SDE1");
		addOffer("SDE2");
		add("abc@gmail.com","Applying for SDE1","APPLIED","SDE1");
		add("xyz@gmail.com","Applying for SDE1","APPLIED","SDE1");
		add("ab@gmail.com","Applying for SDE2","APPLIED","SDE2");
		add("xy@gmail.com","Applying for SDE2","APPLIED","SDE2");
		String jobTitle ="SDE1";
		List<Application> applications = new ArrayList<>();
		applicationRepository.findByOfferJobTitle(jobTitle).forEach(applications::add);
		assertNotNull(applications);	
		assertThat(applications.size()).isEqualTo(applicationRepository.findByOfferJobTitle(jobTitle).size());
		
					
	}

}
