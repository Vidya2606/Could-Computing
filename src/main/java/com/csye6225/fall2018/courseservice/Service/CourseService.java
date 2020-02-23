package com.csye6225.fall2018.courseservice.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.csye6225.fall2018.courseservice.DataBase.CourseDB;
import com.csye6225.fall2018.courseservice.DataBase.ProgramDB;
import com.csye6225.fall2018.courseservice.Exceptions.InvalidParameterException;
import com.csye6225.fall2018.courseservice.Exceptions.ResourseNotFoundException;
import com.csye6225.fall2018.courseservice.Model.Course;
import com.csye6225.fall2018.courseservice.Model.Program;

public class CourseService {
	public HashMap<String, Course> courseMap = CourseDB.getCourseDB();
	public HashMap<String, Program> progMap = ProgramDB.getProgramDB();
	static String coursePrefix = "course";
	
	public CourseService() {	
	}
	
	//Getting list of all courses
	public List<Course> getAllCourses(){
		ArrayList<Course> list = new ArrayList<>();
		for(Course cour: courseMap.values()) {
			list.add(cour);
		}
		return list;
	}
	
	//Adding a course
	public Course addCourse(String courseName, String noOfCredits, String progID) throws Exception {
		validateProgram(progID);
		String courseId = ServiceUtils.generateID(coursePrefix);
		Course cour = new Course(courseId, courseName, noOfCredits, progID);
		//Store the course
		courseMap.put(courseId, cour);
		
		// Store course id in prog
		Program prog = progMap.get(progID);
		List<String> courseInProg = prog.getCourses();
		courseInProg.add(courseName);
		prog.setCourses(courseInProg);
		progMap.put(progID, prog);

		//Return new course
		return cour;
	}
	
	public Course addCourse(Course cour) throws Exception {
		return addCourse(cour.getCourseName(), cour.getNoOfCredits(), cour.getProgID());
	}
	
	//Getting one course
	public Course getCourse(String courseID) throws Exception {
		validateCourse(courseID);
		Course cours = courseMap.get(courseID);
		return cours;
	}
	
	//Deleting a Course
	public Course deleteCourse(String courseID) throws Exception {
		validateCourse(courseID);
		Course deletedCourseDetails = courseMap.get(courseID);
		courseMap.remove(courseID);
		return deletedCourseDetails;
	}
	
	//Updating course Info
	public Course updateCourseInformation(String courseID, Course cours) throws Exception {
		validateCourse(courseID);
		if(cours.getCourseName() == null || cours.getCourseName().length() < 1) {
			throw new InvalidParameterException("Course name is invalid.");
		}
		Course courseToUpdate = courseMap.get(courseID);
		courseToUpdate.setCourseName(cours.getCourseName());
		courseMap.put(courseID, courseToUpdate);
		return courseToUpdate;
	}
	

	//Get Course in a program 
	public List<Course> getCoursesByProgram(String progID) {	
		//Getting the list
		ArrayList<Course> list = new ArrayList<>();
		for (Course cour : courseMap.values()) {	
		if (cour.getProgID().equals(progID)) {
				list.add(cour);
			}
		}
		return list ;
	}
	
	private void validateProgram(String programID) throws Exception {
		if (progMap == null || !progMap.containsKey(programID)) {
			throw new ResourseNotFoundException("programID: " + programID + " was not found.");
		}
	}
	
	private void validateCourse(String courseID) throws Exception {
		if (courseMap == null || !courseMap.containsKey(courseID)) {
			throw new ResourseNotFoundException("courseID: " + courseID + " was not found.");
		}
	}
}
