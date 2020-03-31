package com.csye6225.fall2018.courseservice.Model;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="deptTable")
public class Department {
	private String deptID;
	private String deptName;
	private List<String> programs;
	private List<String> professors;
	
	public Department() {
		this.programs = new ArrayList<String>();
		this.professors = new ArrayList<String>();
	}

    public Department(String deptID, String deptName) {
		this.deptID = deptID;
		this.deptName = deptName;
		this.programs = new ArrayList<String>();
		this.professors = new ArrayList<String>();
	}
    
    @DynamoDBHashKey(attributeName="deptID")
	public String getDeptID() {
		return deptID;
	}
	public void setDeptID(String deprtID) {
		this.deptID = deprtID;
	}
	
	@DynamoDBAttribute(attributeName="deptName")
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String departName) {
		this.deptName = departName;
	}
	
	@DynamoDBAttribute(attributeName="programs")
	public List<String> getPrograms() {
		return programs;
	}
	
	public void setPrograms(List<String> programs) {
		this.programs = programs;
	}
	
	@DynamoDBAttribute(attributeName="professors")
	public List<String> getProfessors() {
		return professors;
	}

	public void setProfessors(List<String> professors) {
		this.professors = professors;
	}
	
	@DynamoDBIgnore
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\"Department\": {\"deptID\"=\"");
		builder.append(deptID);
		builder.append("\", \"departName\"=\"");
		builder.append(deptName);
		builder.append("\"}");
		return builder.toString();
	}
}
