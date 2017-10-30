/**
 * 
 */
package com.recruitment.model;

import java.sql.Date;
/**
 * Apply class is a bean of apply table
 */
public class Apply {

	private Position post;	//object of position
	private Applicant applicant;//object of applicant
	private Date dateOfApply;	//date of apply

	public Apply() {
		super();
	}

	public Apply(Position post, Applicant applicant, Date dateOfApply) {
		super();
		this.post = post;
		this.applicant = applicant;
		this.dateOfApply = dateOfApply;
	}

	public Position getPost() {
		return post;
	}

	public void setPost(Position post) {
		this.post = post;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}

	public Date getDateOfApply() {
		return dateOfApply;
	}

	public void setDateOfApply(Date dateOfApply) {
		this.dateOfApply = dateOfApply;
	}

	@Override
	public String toString() {
		return "ApplicantApply [post=" + post + ", applicant=" + applicant
				+ ", dateOfApply=" + dateOfApply + "]";
	}

}
