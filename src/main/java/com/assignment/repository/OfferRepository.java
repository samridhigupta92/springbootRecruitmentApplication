package com.assignment.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.assignment.models.Application;
import com.assignment.models.Offer;

@Repository
public interface OfferRepository extends CrudRepository<Offer,String> {
	
	
@Modifying
@Transactional
@Query(value = "update Offer o set o.numberOfApplicants = :applicants where o.jobTitle= :jobTitle")
void updateOffer(@Param("jobTitle") String jobTitle, @Param("applicants") int applicants);

}
	


