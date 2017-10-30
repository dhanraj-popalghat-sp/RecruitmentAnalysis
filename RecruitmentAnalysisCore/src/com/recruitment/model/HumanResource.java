package com.recruitment.model;
/**
 * Human resource class is a bean of human
 */
public class HumanResource {

	private int hrId;		//id of human resource
	private String name;	//name of human resource
	private String department;//department of human resource
	private String contact;	//contact no. of human resource
	private String password;//password of human resource

	public HumanResource() {
		super();
	}

	public HumanResource(int hrId, String name, String department,
			String contact, String password) {
		super();
		this.hrId = hrId;
		this.name = name;
		this.department = department;
		this.contact = contact;
		this.password = password;
	}

	public HumanResource(String name, String department, String contact,
			String password) {
		super();
		this.name = name;
		this.department = department;
		this.contact = contact;
		this.password = password;
	}

	public int getHrId() {
		return hrId;
	}

	public void setHrId(int hrId) {
		this.hrId = hrId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "HumanResource [hrId=" + hrId + ", name=" + name
				+ ", department=" + department + ", contact=" + contact
				+ ", password=" + password + "]";
	}

}
