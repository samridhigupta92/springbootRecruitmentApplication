package com.assignment.api;
import com.assignment.exception.DataNotFoundException;
import com.assignment.models.Application;
import com.assignment.models.Offer;
import com.assignment.service.*;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import javax.validation.Valid;

@RestController
public class OfferRestController {
	@Autowired
	private KieSession session;
	
	@Autowired
	private OfferService offerService;
	
	@RequestMapping("/offers")
	public ResponseEntity<List<Offer>> getAllOffers()
	{
		List<Offer> offers = new ArrayList<>();
		offerService.getAllOffers().forEach(offers::add);
		return new ResponseEntity<List<Offer>>(offers,HttpStatus.OK);
	}
	
	@RequestMapping("/offers/{jobTitle}")
	public ResponseEntity<Offer> getOffer(@PathVariable String jobTitle)
	{
		Offer offer = new Offer();
		offer= offerService.getOffer(jobTitle);
		return new ResponseEntity<Offer>(offer,HttpStatus.OK);
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value="/offers")

	public ResponseEntity<Offer> addOffer(@Valid @RequestBody Offer offer) throws MethodArgumentNotValidException 
	{
		session.insert(offer);
		session.fireAllRules();
		 offerService.addOffer(offer);
		 return new ResponseEntity<Offer>(offer,HttpStatus.OK);
	}
	
	

}
