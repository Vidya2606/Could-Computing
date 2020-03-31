package com.csye6225.fall2018.courseservice.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.csye6225.fall2018.courseservice.DataBase.CourseDB;
import com.csye6225.fall2018.courseservice.DataBase.ProgramDB;
import com.csye6225.fall2018.courseservice.Exceptions.InvalidParameterException;
import com.csye6225.fall2018.courseservice.Exceptions.ResourseNotFoundException;
import com.csye6225.fall2018.courseservice.Model.Course;
import com.csye6225.fall2018.courseservice.Model.DynamoDbConnector;
import com.csye6225.fall2018.courseservice.Model.Program;

public class CourseService {
	static HashMap<String, Course> courseMap = CourseDB.getCourseDB();
	static HashMap<String, Program> progMap = ProgramDB.getProgramDB();
	static DynamoDbConnector dynamoDb;
	DynamoDBMapper mapper;
	String coursePrefix = "course";
	
	public CourseService() {
		dynamoDb = new DynamoDbConnector();
		DynamoDbConnector.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}
	
	//Getting list of all courses
	public List<Course> getAllCourses(){
		initDDBConnectors();
		List<Course> allCourses = mapper.scan(Course.class, new DynamoDBScanExpression());
		return allCourses;
	}
	
	//Adding a course
	public Course addCourse(String courseName, String noOfCredits, String progID) throws Exception {
		Program prog = getAndValidateProgram(progID);
		initDDBConnectors();
		String courseId = ServiceUtils.generateID(coursePrefix);
		Course course = new Course(courseId, courseName, noOfCredits, progID);
		//Store the course
		mapper.save(course);
		
		// Store course id in prog
		List<String> courseInProg = prog.getCourses();
		courseInProg.add(courseName);
		prog.setCourses(courseInProg);
		mapper.save(prog);
		//Return new course
		return course;
	}
	
	public Course addCourse(Course cour) throws Exception {
		return addCourse(cour.getCourseName(), cour.getNoOfCredits(), cour.getProgID());
	}
	
	//Getting one course
	public Course getCourse(String courseID) throws Exception {
		initDDBConnectors();
		return getAndValidateCourse(courseID);
	}
	
	//Deleting a Course
	public Course deleteCourse(String courseID) throws Exception {
		Course deletedCourseDetails = getAndValidateCourse(courseID);
		mapper.delete(deletedCourseDetails);
		return deletedCourseDetails;
	}
	
	//Updating course Info
	public Course updateCourseInformation(String courseID, Course course) throws Exception {
		Course courseToUpdate = getAndValidateCourse(courseID);
		if(course.getCourseName() == null || course.getCourseName().length() < 1) {
			throw new InvalidParameterException("Course name is invalid.");
		}
		courseToUpdate.setCourseName(course.getCourseName());
		mapper.save(courseToUpdate);
		return courseToUpdate;
	}
	
	//Get Course in a program
	public List<Course> getCoursesByProgram(String progID) {	
		//Getting the list
		ArrayList<Course> list = new ArrayList<>();
		List<Course> allCourses = getAllCourses();
		for (Course cour : allCourses) {	
		if (cour.getProgID().equals(progID)) {
				list.add(cour);
			}
		}
		return list ;
	}
	
	private Program getAndValidateProgram(String programID) throws Exception {
		initDDBConnectors();
		Program prog = mapper.load(Program.class, programID);
		if (prog == null) {
			throw new ResourseNotFoundException("programID: " + programID + " not found.");
		}
		return prog;
	}
	
	private Course getAndValidateCourse(String courseID) throws Exception {
		initDDBConnectors();
		Course course = mapper.load(Course.class, courseID);
		if (course == null) {
			throw new ResourseNotFoundException("courseID: " + courseID + " not found.");
		}
		return course;
	}
	
	private void initDDBConnectors() {
		if (dynamoDb == null || mapper == null) {
		    dynamoDb = new DynamoDbConnector();
		    DynamoDbConnector.init();
		    mapper = new DynamoDBMapper(dynamoDb.getClient());
		}
	}
}
