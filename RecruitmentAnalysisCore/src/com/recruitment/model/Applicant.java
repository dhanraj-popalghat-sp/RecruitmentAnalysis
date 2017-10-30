package com.recruitment.model;

/**
 * Applicant class is a bean of applicant
 */
public class Applicant {

	private int applicantId;
	private String name;
	private double experience;
	private String highest_qual;
	private String email;
	private String contact;

	public Applicant() {
		super();
	}

	public Applicant(int applicantId, String name, double experience,
			String highest_qual, String email, String contact) {
		super();
		this.applicantId = applicantId;
		this.name = name;
		this.experience = experience;
		this.highest_qual = highest_qual;
		this.email = email;
		this.contact = contact;
	}

	public Applicant(String name, double experience, String highest_qual,
			String email, String contact) {
		super();
		this.name = name;
		this.experience = experience;
		this.highest_qual = highest_qual;
		this.email = email;
		this.contact = contact;
	}

	public int getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(int applicantId) {
		this.applicantId = applicantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getExperience() {
		return experience;
	}

	public void setExperience(double experience) {
		this.experience = experience;
	}

	public String getHighest_qual() {
		return highest_qual;
	}

	public void setHighest_qual(String highest_qual) {
		this.highest_qual = highest_qual;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Override
	public String toString() {
		return "Applicant [applicantId=" + applicantId + ", name=" + name
				+ ", experience=" + experience + ", highest_qual="
				+ highest_qual + ", email=" + email + ", contact=" + contact
				+ "]";
	}
}
