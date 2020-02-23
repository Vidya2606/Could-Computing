	package com.csye6225.fall2018.courseservice.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.csye6225.fall2018.courseservice.DataBase.ProgramDB;
import com.csye6225.fall2018.courseservice.DataBase.StudentDB;
import com.csye6225.fall2018.courseservice.Exceptions.InvalidParameterException;
import com.csye6225.fall2018.courseservice.Exceptions.ResourseNotFoundException;
import com.csye6225.fall2018.courseservice.Model.Program;
import com.csye6225.fall2018.courseservice.Model.Student;

public class StudentService {
	static HashMap<String, Student> studentMap = StudentDB.getStudentDB();
	static HashMap<String, Program> progmMap = ProgramDB.getProgramDB();
	String studPrefix = "student";
	
	public StudentService() {
	}
	
	//Getting the list of students
	//GET "..webapi/students
	public List<Student> getAllStudents() {
		//Getting the list
		ArrayList<Student> list = new ArrayList<>();
		for(Student students: studentMap.values()) {
			list.add(students);
		}
		return list;
	}
	
	//Adding a student
	public Student addStudent(String firstName, String lastName, String programID, String emailID, Date joiningDate) throws Exception {
		validateProgram(programID);
		//Create a student object		
		String studentID = ServiceUtils.generateID(studPrefix);
		Student student = new Student(studentID, firstName, lastName, emailID, programID, joiningDate.toString());
		studentMap.put(studentID,student);
		return student;
	}
	
	public Student addStudent(Student stud) throws Exception {
		Date joiningDate = new Date();
		return addStudent(stud.getFirstName(), stud.getLastName(), stud.getProgramID(), stud.getEmailID(), joiningDate);
	}
	
	//Getting one student
	public Student getStudent(String studentID) throws Exception {
		validateStudent(studentID);
		Student stud = studentMap.get(studentID);
		return stud;
	}
	
	//Deleting student
	public Student deleteStudent(String studentID) throws Exception {
		validateStudent(studentID);
		Student deletedStudentDetails = studentMap.get(studentID);
		studentMap.remove(studentID);
		return deletedStudentDetails;
	}
	
	//Updating student info
	public Student updateStudentInfo(String studentID, Student stud) throws Exception {
		validateStudent(studentID);
		Student studentToUpdate = studentMap.get(studentID);
		if (stud.getEmailID() == null || stud.getEmailID().length() < 3) {
			throw new InvalidParameterException("EmailID is invalid.");
		}
		studentToUpdate.setEmailID(stud.getEmailID());
		studentMap.put(studentID, studentToUpdate);
		return studentToUpdate;
	}
	
	//Get all students in a program
	public List<Student> getStudentByProgram(String program) {
		ArrayList<Student> list = new ArrayList<>();
		for(Student student: studentMap.values()) {
			if(student.getProgramID().equals(program)) {
				list.add(student);
			}
		}
		return list;
	}
	
	private void validateStudent(String studentID) throws Exception {
		if (studentMap == null || !studentMap.containsKey(studentID)) {
			throw new ResourseNotFoundException("studentID: " + studentID + " was not found.");
		}
	}
	
	private void validateProgram(String programID) throws Exception {
		if (progmMap == null || !progmMap.containsKey(programID)) {
			throw new ResourseNotFoundException("programID: " + programID + " was not found.");
		}
	}
}
