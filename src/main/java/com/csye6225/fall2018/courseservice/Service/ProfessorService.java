package com.csye6225.fall2018.courseservice.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.csye6225.fall2018.courseservice.DataBase.DataBaseClass;
import com.csye6225.fall2018.courseservice.DataBase.DepartmentDB;
import com.csye6225.fall2018.courseservice.Exceptions.InvalidParameterException;
import com.csye6225.fall2018.courseservice.Exceptions.ResourseNotFoundException;
import com.csye6225.fall2018.courseservice.Model.Department;
import com.csye6225.fall2018.courseservice.Model.DynamoDbConnector;
import com.csye6225.fall2018.courseservice.Model.Professor;

public class ProfessorService {
	static HashMap<String, Professor> prof_Map = DataBaseClass.getProfessorDB();
	static HashMap<String, Department> deptMap = DepartmentDB.getdeptDB();
	static DynamoDbConnector dynamoDb;
	static DynamoDBMapper mapper; 
	static String profPrefix = "prof";
	
	public void ProfessorsService() {
		dynamoDb = new DynamoDbConnector();
		DynamoDbConnector.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
		System.out.println("#### ProfessorsService initialized");
	}
	
	// Getting a list of all professor 
	// GET "..webapi/professors"
	public List<Professor> getAllProfessors() {	
		initDDBConnectors();
		//Getting the list
		List<Professor> allProfs = mapper.scan(Professor.class, new DynamoDBScanExpression());
		return allProfs;
	}

	// Adding a professor
	public Professor addProfessor(String firstName, String lastName, String deptID, Date joiningDate) throws Exception { 
		Department dept = getAndValidateDept(deptID);
		initDDBConnectors();
		String profID = ServiceUtils.generateID(profPrefix);
		Professor prof = new Professor(profID, firstName , lastName, deptID, joiningDate.toString());
		System.out.println("#### Saving prof");
		mapper.save(prof);

		// Store prof in dept
		List<String> professorsInDept = dept.getProfessors();
		professorsInDept.add(profID);
		dept.setProfessors(professorsInDept);
		mapper.save(dept);
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
		initDDBConnectors();
		// Professor prof = mapper.load(Professor.class, profId);
	    return getAndValidateProfessor(profId);
	}
	
	// Deleting a professor
	public Professor deleteProfessor(String profId) throws Exception {
		Professor deletedProfDetails = getAndValidateProfessor(profId);
		mapper.delete(deletedProfDetails);
		return deletedProfDetails;
	}
	
	// Updating Professor Info
	public Professor updateProfessorInformation(String profId, Professor prof) throws Exception {	
		// 
		Professor profToUpdate = getAndValidateProfessor(profId);

		if (prof.getFirstName() == null || prof.getFirstName().length() < 1) {
			throw new InvalidParameterException("First Name is invalid.");
		}
		profToUpdate.setFirstName(prof.getFirstName());
		mapper.save(profToUpdate);
		return profToUpdate;
	}
	
	// Get professors in a department
	public List<Professor> getProfessorsByDepartment(String deptID) {	
		//Getting the list
		ArrayList<Professor> result = new ArrayList<Professor>();
		List<Professor> allProfs = getAllProfessors();
		for (Professor prof : allProfs) {
			if (prof.getDeptID().equals(deptID)) {
				result.add(prof);
			}
		}
		return result;
	}

	private Professor getAndValidateProfessor(String profID) throws Exception {
		initDDBConnectors();
		Professor prof = mapper.load(Professor.class, profID);
		if (prof == null) {
			throw new ResourseNotFoundException("profID: " + profID + " not found.");
		}
		return prof;
	 }
	
	private Department getAndValidateDept(String deptID) throws Exception {
		initDDBConnectors();
		Department dept = mapper.load(Department.class, deptID);
		if(dept == null) {
			throw new ResourseNotFoundException("DeptID: " + deptID + " not found.");
		}
		return dept;
	}
	
	private void initDDBConnectors() {
		if (dynamoDb == null || mapper == null) {
		    dynamoDb = new DynamoDbConnector();
		    DynamoDbConnector.init();
		    mapper = new DynamoDBMapper(dynamoDb.getClient());
		}
	}
}
