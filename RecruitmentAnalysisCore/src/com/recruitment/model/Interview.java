package com.recruitment.model;

import java.sql.Date;
/**
 * Interview class is a bean of interview
 */
public class Interview {

	private Applicant applicant; //object of applicant
	private Position position;	//object of position
	private Date dateOfInterview;//date of interview 	
	private Date dateOfJoining;	//date of joining
	private String location;	//location of interview
	private boolean applicantResult;	//result of applicant
	private boolean joiningStatus;	// joining status of applicant

	public Interview() {
		super();
	}

	public Interview(Applicant applicant, Position position,
			Date dateOfInterview, Date dateOfJoining, String location,
			boolean applicantResult, boolean joiningStatus) {
		super();
		this.applicant = applicant;
		this.position = position;
		this.dateOfInterview = dateOfInterview;
		this.dateOfJoining = dateOfJoining;
		this.location = location;
		this.applicantResult = applicantResult;
		this.joiningStatus = joiningStatus;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Date getDateOfInterview() {
		return dateOfInterview;
	}

	public void setDateOfInterview(Date dateOfInterview) {
		this.dateOfInterview = dateOfInterview;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public boolean isApplicantResult() {
		return applicantResult;
	}

	public void setApplicantResult(boolean applicantResult) {
		this.applicantResult = applicantResult;
	}

	public boolean isJoiningStatus() {
		return joiningStatus;
	}

	public void setJoiningStatus(boolean joiningStatus) {
		this.joiningStatus = joiningStatus;
	}

	public Date getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	@Override
	public String toString() {
		return "Interview [applicant=" + applicant + ", position=" + position
				+ ", dateOfInterview=" + dateOfInterview + ", dateOfJoining="
				+ dateOfJoining + ", location=" + location
				+ ", applicantResult=" + applicantResult + ", joiningStatus="
				+ joiningStatus + "]";
	}
}
