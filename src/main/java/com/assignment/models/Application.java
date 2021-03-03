package com.assignment.models;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Application {
	@Id	
	@Column(name="EMAIL")
	@NotBlank(message ="Email must not be blank")
	@Email(message = "Please input correct email format")
	private String email;	
	
	
	@Column(name="RESUME_TEXT")	
	@NotEmpty(message ="Resume text must not be blank")
	private String resumeText;
	
	@Column(name="GRADE")	
	private String grade;
	
	
	
	@NotEmpty(message ="Application status must not be blank")
	@Column(name="APPLICATION_STATUS")
	private String applicationStatus;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonBackReference
	private Offer offer;
	
	public Application(String jobTitle, String email, String resumeText, String applicationStatus){
		
		this.offer= new Offer(jobTitle);
		this.email=email;
		this.resumeText=resumeText;
		this.applicationStatus=applicationStatus;
		
		
	}
	
    public Application(){
		
	}
	
	

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getResumeText() {
		return resumeText;
	}

	public void setResumeText(String resumeText) {
		this.resumeText = resumeText;
	}

	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	
	

}
