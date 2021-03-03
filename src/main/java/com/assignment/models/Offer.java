package com.assignment.models;
import java.time.LocalDate;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="OFFERS")
public class Offer {	
	
	@Id
	@Column(name ="JOB_TITLE")
	@NotEmpty(message="Job title must not be empty")
	private String jobTitle;
	
	@Column(name ="JOINING_BONUS")
	private int joiningBonus;
	
	

	@Column(name="START_DATE")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@NotNull(message="Please provide a date")
	private Date startDate; 
	
	@Column(name="NUMBER_OF_APPLICANTS")
	@NotNull(message ="Number of applicants must not be blank")
    private int numberOfApplicants;
	
	@OneToMany( mappedBy = "offer",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<Application> applications;
	
	public Offer()
	{
		
	}
	
	public Offer(String jobTitle)
	{
		this.jobTitle=jobTitle;
		
		
	}
	public Offer(String jobTitle, Date startDate)
	{
		this.jobTitle=jobTitle;
		this.startDate=startDate;
		
	}
	
	public Offer(String jobTitle, Date startDate, int numberOfApplicants)
	{
		this.jobTitle=jobTitle;
		this.startDate=startDate;
		this.numberOfApplicants=numberOfApplicants;
		
	}
	

	public List<Application> getApplications() {
		return applications;
	}


	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}




	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public int getNumberOfApplicants() {
		return numberOfApplicants;
	}

	public void setNumberOfApplicants(int numberOfApplicants) {
		this.numberOfApplicants = numberOfApplicants;
	}
	
	public int getJoiningBonus() {
		return joiningBonus;
	}

	public void setJoiningBonus(int joiningBonus) {
		this.joiningBonus = joiningBonus;
	}	

}
