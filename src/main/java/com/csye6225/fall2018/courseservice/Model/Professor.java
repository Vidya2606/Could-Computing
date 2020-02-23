package com.csye6225.fall2018.courseservice.Model;

public class Professor {
	private String firstName;
	private String lastName;
	private String deptID;
	private String profID;
	private String joiningDate;
	
	public Professor() {
	}
	
	public Professor(String profID, String firstName, 
			String lastName, String deptID, String joiningDate) {
		this.profID = profID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.deptID = deptID;
		this.joiningDate = joiningDate;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getDeptID() {
		return deptID;
	}
	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}

	public String getProfID() {
		return profID;
	}
	public void setProfID(String profID) {
		this.profID = profID;
	}
	
	public String getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}

	@Override
	public String toString() { 
		return "ProfId=" + getProfID() + ", firstName=" + getFirstName()
				+ ", deptID=" + getDeptID() + ", joiningDate=" + getJoiningDate();
	}

	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}


