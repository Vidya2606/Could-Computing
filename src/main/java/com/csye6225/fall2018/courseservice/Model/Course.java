package com.csye6225.fall2018.courseservice.Model;

import java.util.List;

public class Course {
	private String courseID;
	private String courseName;
	private String noOfCredits;
	private String progID;
	private List<String> program;
	private String profID;
	
	public Course() {
	}
	
	public Course(String courseID, String courseName, String noOfCredits, String progID) {
		this.courseID = courseID;
		this.courseName = courseName;
		this.noOfCredits = noOfCredits;
		this.progID = progID;
	}

	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getNoOfCredits() {
		return noOfCredits;
	}

	public void setNoOfCredits(String noOfCredits) {
		this.noOfCredits = noOfCredits;
	}
	
	public List<String> getProgram() {
		return program;
	}

	public void setProgram(List<String> program) {
		this.program = program;
	}

	public String getProgID() {
		return progID;
	}

	public void setProgID(String progID) {
		this.progID = progID;
	}

	public String getProfID() {
		return profID;
	}

	public void setProfID(String profID) {
		this.profID = profID;
	}

	@Override
	public String toString() {
		return "CourseID" + getCourseID() + "CourseName" + getCourseName() + 
				"NoOfCredits" + getNoOfCredits() + "ProgID" + getProgID();
	}

}
