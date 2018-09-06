package com.cg.uas.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="programs_offered")
public class ProgramOfferedBean {

	
	@Id
	@Pattern(regexp="[A-Za-z]{1,5}", message="Please enter a valid Program Name")
	@NotEmpty(message="Field cannot be empty")
	@Column(name="PROGRAMNAME")
	private String programName;
	
	@NotEmpty(message="Field cannot be empty")
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="APPLICANT_ELIGIBILITY")	
	@NotEmpty(message="Field cannot be empty")
	private String applicantEligibility;
	
	@Column(name="DURATION")
	@NotEmpty(message="Field cannot be empty")
	private String duration;
	
	@Column(name="DEGREE_CERTIFICATE_OFFERED")
	@NotEmpty(message="Field cannot be empty")
	private String degreeCertificateOffered;
	
	
	
	
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getApplicantEligibility() {
		return applicantEligibility;
	}
	public void setApplicantEligibility(String applicantEligibility) {
		this.applicantEligibility = applicantEligibility;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getDegreeCertificateOffered() {
		return degreeCertificateOffered;
	}
	public void setDegreeCertificateOffered(String degreeCertificateOffered) {
		this.degreeCertificateOffered = degreeCertificateOffered;
	}
	@Override
	public String toString() {
		return "programName=" + programName + ", description="
				+ description + ", applicantEligibility="
				+ applicantEligibility + ", duration=" + duration
				+ ", degreeCertificateOffered=" + degreeCertificateOffered;
	}
	
	
	
}
