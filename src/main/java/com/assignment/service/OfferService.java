package com.assignment.service;

import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.assignment.exception.DataNotFoundException;
import com.assignment.models.Application;
import com.assignment.models.Offer;
import com.assignment.repository.*;

@Service
public class OfferService { 
	
	@Autowired
	private OfferRepository offerRepository;
	
	
	public List<Offer> getAllOffers()
	{				
		
		List<Offer> offers = new ArrayList<>();
		offerRepository.findAll().forEach(offers::add);
		if(offers.size() ==0)
			throw new DataNotFoundException("No job offers found");
			
		return offers;

	}
	
	public Offer getOffer(String jobTitle) throws DataNotFoundException
	{
		
		Offer offer = offerRepository.findById(jobTitle).orElse(null);
		if(offer ==null)		
			throw new DataNotFoundException("Offer with job title "+jobTitle+" not found");
		
		return offer;
	}
	
	public void addOffer(Offer offer)
	{				
	    offerRepository.save(offer);
	}
	
	public void updateOffer(Offer offer)
	{				
	    offerRepository.save(offer);
	}

	
		
	}

