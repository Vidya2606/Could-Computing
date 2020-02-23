package com.csye6225.fall2018.courseservice.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.csye6225.fall2018.courseservice.DataBase.CourseDB;
import com.csye6225.fall2018.courseservice.DataBase.DataBaseClass;
import com.csye6225.fall2018.courseservice.DataBase.DepartmentDB;
import com.csye6225.fall2018.courseservice.Exceptions.InvalidParameterException;
import com.csye6225.fall2018.courseservice.Exceptions.ResourseNotFoundException;
import com.csye6225.fall2018.courseservice.Model.Course;
import com.csye6225.fall2018.courseservice.Model.Department;
//import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
//import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
//import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
//import com.csye6225.fall2018.courseservice.Model.DynamoDbConnector;
//import com.csye6225.fall2018.courseservice.Model.InMemoryDatabase;
import com.csye6225.fall2018.courseservice.Model.Professor;

public class ProfessorService {
	static HashMap<String, Professor> prof_Map = DataBaseClass.getProfessorDB();
	static HashMap<String, Department> deptMap = DepartmentDB.getdeptDB();
	String profPrefix = "prof";
	
	public void ProfessorsService() {
	}
	
	// Getting a list of all professor 
	// GET "..webapi/professors"
	public List<Professor> getAllProfessors() {	
		//Getting the list
		ArrayList<Professor> list = new ArrayList<>();
		for (Professor prof : prof_Map.values()) {
			list.add(prof);
		}
		return list;
	}

	// Adding a professor
	public Professor addProfessor(String firstName, String lastName, String deptID, Date joiningDate) throws Exception { 
		validateDept(deptID);
		String profID = ServiceUtils.generateID(profPrefix);
		//Create a Professor Object
		Professor prof = new Professor(profID, firstName , lastName, deptID, joiningDate.toString());
		prof_Map.put(profID, prof);
		
		// Store prof in dept
		Department dept = deptMap.get(deptID);
		List<String> professorsInDept = dept.getProfessors();
		professorsInDept.add(profID);
		dept.setProfessors(professorsInDept);
		deptMap.put(deptID, dept);

		//return new professor
		return prof;	
	}
	
	// Adding a professor.
	// Date Format: YY-MM-DD, MM-DD-CCYY
	public Professor addProfessor(Professor prof) throws Exception {
		Date joiningDate = new Date();
		return addProfessor(prof.getFirstName(), prof.getLastName(), prof.getDeptID(), joiningDate);
	}
	
	// Getting One Professor
	public Professor getProfessor(String profId) throws Exception {
		validateProfessor(profId);
		Professor prof2 = prof_Map.get(profId);
	    return prof2;
	}
	
	// Deleting a professor
	public Professor deleteProfessor(String profId) throws Exception {
		validateProfessor(profId);
		Professor deletedProfDetails = prof_Map.get(profId);
		prof_Map.remove(profId);
		return deletedProfDetails;
	}
	
	// Updating Professor Info
	public Professor updateProfessorInformation(String profId, Professor prof) throws Exception {	
		validateProfessor(profId);
		Professor profToUpdate = prof_Map.get(profId);
		if (prof.getFirstName() == null || prof.getFirstName().length() < 1) {
			throw new InvalidParameterException("First Name is invalid.");
		}
		profToUpdate.setFirstName(prof.getFirstName());
		prof_Map.put(profId, profToUpdate);
		return profToUpdate;
	}
	
	// Get professors in a department 
	public List<Professor> getProfessorsByDepartment(String department) {	
		//Getting the list
		ArrayList<Professor> list = new ArrayList<>();
		for (Professor prof : prof_Map.values()) {
			if (prof.getDeptID().equals(department)) {
				list.add(prof);
			}
		}
		return list ;
	}

	private void validateProfessor(String profID) throws Exception {
		if(prof_Map == null || !prof_Map.containsKey(profID)) {
			throw new ResourseNotFoundException("profID: " + profID + " not found.");
		}
	 }
	
	private void validateDept(String deptID) throws Exception {
		if(deptMap == null || !deptMap.containsKey(deptID)) {
			throw new ResourseNotFoundException("DeptID: " + deptID + " not found.");
		}
	}
}
