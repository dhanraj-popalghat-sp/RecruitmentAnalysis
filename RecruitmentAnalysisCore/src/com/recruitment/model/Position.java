package com.recruitment.model;

import java.sql.Date;
/**
 * Position class is a bean of post
 */
public class Position {

	private int postId;			//id of position
	private String postName;	//name of position
	private int noOfPosts;		//number of post available
	private double experience;	//experience of position
	private HumanResource hr;	//Human resource object
	private Date dateOfPost;	//Date Of post
	private boolean status;		//status of position

	public Position() {
		super();
	}

	public Position(String postName, int noOfPosts, double experience,
			Date dateOfPost, boolean status) {
		super();
		this.postName = postName;
		this.noOfPosts = noOfPosts;
		this.experience = experience;
		this.dateOfPost = dateOfPost;
		this.status = status;
	}

	public Position(int postId, String postName, int noOfPosts,
			double experience, Date dateOfPost, boolean status) {
		super();
		this.postId = postId;
		this.postName = postName;
		this.noOfPosts = noOfPosts;
		this.experience = experience;
		this.dateOfPost = dateOfPost;
		this.status = status;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public int getNoOfPosts() {
		return noOfPosts;
	}

	public void setNoOfPosts(int noOfPosts) {
		this.noOfPosts = noOfPosts;
	}

	public double getExperience() {
		return experience;
	}

	public void setExperience(double experience) {
		this.experience = experience;
	}

	public HumanResource getHr() {
		return hr;
	}

	public void setHr(HumanResource hr) {
		this.hr = hr;
	}

	public Date getDateOfPost() {
		return dateOfPost;
	}

	public void setDateOfPost(Date dateOfPost) {
		this.dateOfPost = dateOfPost;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Position [postId=" + postId + ", postName=" + postName
				+ ", noOfPosts=" + noOfPosts + ", experience=" + experience
				+ ", hr=" + hr + ", dateOfPost=" + dateOfPost + ", status="
				+ status + "]";
	}

}
