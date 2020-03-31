package com.csye6225.fall2018.courseservice.Model;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="programTable")
public class Program {
	private String programID;
	private String programName;
	private String deptID;
	private List<String> courses;
	
	public Program() {
		this.courses = new ArrayList<String>();
	}
	
	public Program(String programID, String programName, String deptID) {
		this.programID = programID;
		this.programName = programName;
		this.deptID = deptID;
		this.courses = new ArrayList<String>();
	}
	
	@DynamoDBHashKey(attributeName="progID")
	public String getProgramID() {
		return programID;
	}
	
	public void setProgramID(String programID) {
		this.programID = programID;
	}
	
	@DynamoDBAttribute(attributeName="progName")
	public String getProgramName() {
		return programName;
	}
	
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	
	@DynamoDBAttribute(attributeName="courses")
	public List<String> getCourses() {
		return this.courses;
	}
	public void setCourses(List<String> courses) {
		this.courses = courses;
	}
	
	@DynamoDBAttribute(attributeName="deptID")
	public String getDeptID() {
		return deptID;
	}
	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}

	@DynamoDBIgnore
	@Override
	public String toString() {
		return "ProgramID=" + getProgramID() + "ProgramName=" + getProgramName(); 
	}
}
