package com.csye6225.fall2018.courseservice.Model;

import java.util.ArrayList;
import java.util.List;

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

	public String getDeptID() {
		return deptID;
	}
	public void setDeptID(String deprtID) {
		this.deptID = deprtID;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String departName) {
		this.deptName = departName;
	}
	
	public List<String> getPrograms() {
		return programs;
	}

	public void setPrograms(List<String> programs) {
		this.programs = programs;
	}

	public List<String> getProfessors() {
		return professors;
	}

	public void setProfessors(List<String> professors) {
		this.professors = professors;
	}

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
