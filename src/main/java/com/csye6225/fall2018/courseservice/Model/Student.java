package com.csye6225.fall2018.courseservice.Model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="studentTable")
public class Student {
	private String studentID;
	private String firstName;
	private String lastName;
	private String emailID;
	private String programID;
	private String joiningDate;

	public Student() {
		
	}
	
	public Student(String studentID, String firstName, String lastName, 
			String emailID, String programID, String joiningDate) {
		
		this.studentID = studentID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailID = emailID;
		this.programID = programID;
		this.joiningDate = joiningDate;
	}
	
	@DynamoDBHashKey(attributeName="studentID")
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
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
	
	@DynamoDBAttribute(attributeName="emailID")
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	
	@DynamoDBAttribute(attributeName="progID")
	public String getProgramID() {
		return programID;
	}
	public void setProgramID(String programID) {
		this.programID = programID;
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
		return "studentID=" + getStudentID() + "firstName=" + getFirstName() +
				"programID=" + getProgramID() + "joiningDate=" + getJoiningDate();
	}
}


