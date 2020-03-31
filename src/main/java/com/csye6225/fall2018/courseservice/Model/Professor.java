package com.csye6225.fall2018.courseservice.Model;
import javax.xml.bind.annotation.XmlRootElement;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="professorTable")
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
	
	@DynamoDBAttribute(attributeName="firstName")
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@DynamoDBAttribute(attributeName="lastName")
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@DynamoDBAttribute(attributeName="deptID")  // was DBAttribute changed to DBHashKey	
	public String getDeptID() {
		return deptID;
	}
	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}
	
	@DynamoDBHashKey(attributeName="profID")
	public String getProfID() {
		return profID;
	}
	public void setProfID(String profID) {
		this.profID = profID;
	}
	
	@DynamoDBAttribute(attributeName="joiningDate")
	public String getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}

	@DynamoDBIgnore
	@Override
	public String toString() { 
		return "ProfId=" + getProfID() + ", firstName=" + getFirstName()
				+ ", deptID=" + getDeptID() + ", joiningDate=" + getJoiningDate();
	}
}


