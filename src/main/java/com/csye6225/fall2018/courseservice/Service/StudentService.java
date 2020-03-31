	package com.csye6225.fall2018.courseservice.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.csye6225.fall2018.courseservice.DataBase.ProgramDB;
import com.csye6225.fall2018.courseservice.DataBase.StudentDB;
import com.csye6225.fall2018.courseservice.Exceptions.InvalidParameterException;
import com.csye6225.fall2018.courseservice.Exceptions.ResourseNotFoundException;
import com.csye6225.fall2018.courseservice.Model.Department;
import com.csye6225.fall2018.courseservice.Model.DynamoDbConnector;
import com.csye6225.fall2018.courseservice.Model.Program;
import com.csye6225.fall2018.courseservice.Model.Student;

public class StudentService {
	static HashMap<String, Student> studentMap = StudentDB.getStudentDB();
	static HashMap<String, Program> progmMap = ProgramDB.getProgramDB();
	static DynamoDbConnector dynamoDb;
	DynamoDBMapper mapper; 
	String studPrefix = "student";
	
	public StudentService() {
		dynamoDb = new DynamoDbConnector();
		DynamoDbConnector.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}
	
	//Getting the list of students
	//GET "..webapi/students
	public List<Student> getAllStudents() {
		initDDBConnectors();
		List<Student> allStudents = mapper.scan(Student.class, new DynamoDBScanExpression());
		return allStudents;
	}
	
	//Adding a student
	public Student addStudent(String firstName, String lastName, String programID, String emailID, Date joiningDate) throws Exception {
		initDDBConnectors();		
		String studentID = ServiceUtils.generateID(studPrefix);
		Student student = new Student(studentID, firstName, lastName, emailID, programID, joiningDate.toString());
		mapper.save(student);
		return student;
	}
	
	public Student addStudent(Student stud) throws Exception {
		Date joiningDate = new Date();
		return addStudent(stud.getFirstName(), stud.getLastName(), stud.getProgramID(), stud.getEmailID(), joiningDate);
	}
	
	//Getting one student
	public Student getStudent(String studentID) throws Exception {
		initDDBConnectors();
		return getAndValidateStudent(studentID);
	}
	
	//Deleting a student
	public Student deleteStudent(String studentID) throws Exception {
		Student deletedStudentDetails = getAndValidateStudent(studentID);
		mapper.delete(deletedStudentDetails);
		return deletedStudentDetails;
	}
	
	//Updating student info
	public Student updateStudentInfo(String studentID, Student stud) throws Exception {
		Student studentToUpdate = getAndValidateStudent(studentID);
		if (stud.getEmailID() == null || stud.getEmailID().length() < 3) {
			throw new InvalidParameterException("EmailID is invalid.");
		}
		studentToUpdate.setEmailID(stud.getEmailID());
		mapper.save(studentToUpdate);
		return studentToUpdate;
	}
	
	//Get all students in a program
	public List<Student> getStudentByProgram(String program) {
		ArrayList<Student> list = new ArrayList<Student>();
		List<Student> allStudents = getAllStudents();
		for(Student student: allStudents) {
			if(student.getProgramID().equals(program)) {
				list.add(student);
			}
		}
		return list;
	}
	
	private Student getAndValidateStudent(String studentID) throws Exception {
		initDDBConnectors();
		Student student = mapper.load(Student.class, studentID);
		if (student == null) {
			throw new ResourseNotFoundException("studentID: " + studentID + " not found.");
		}
		return student;
	}
	
	private void initDDBConnectors() {
		if (dynamoDb == null || mapper == null) {
		    dynamoDb = new DynamoDbConnector();
		    DynamoDbConnector.init();
		    mapper = new DynamoDBMapper(dynamoDb.getClient());
		}
	}
}
