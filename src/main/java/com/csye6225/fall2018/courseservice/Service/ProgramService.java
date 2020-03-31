package com.csye6225.fall2018.courseservice.Service;

import java.util.HashMap;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.csye6225.fall2018.courseservice.DataBase.DepartmentDB;
import com.csye6225.fall2018.courseservice.DataBase.ProgramDB;
import com.csye6225.fall2018.courseservice.Exceptions.InvalidParameterException;
import com.csye6225.fall2018.courseservice.Exceptions.ResourseNotFoundException;
import com.csye6225.fall2018.courseservice.Model.Department;
import com.csye6225.fall2018.courseservice.Model.DynamoDbConnector;
import com.csye6225.fall2018.courseservice.Model.Program;

public class ProgramService {
	static HashMap<String, Program> programMap = ProgramDB.getProgramDB();
	static HashMap<String, Department> deptMap = DepartmentDB.getdeptDB();
	static DynamoDbConnector dynamoDb;
	DynamoDBMapper mapper;
	static String progPrefix = "prog";
	
	public ProgramService() {
		dynamoDb = new DynamoDbConnector();
		DynamoDbConnector.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}
	
	//Getting list of all programs
	//GET "..webapi/programs"
	public List<Program> getAllPrograms(){
		initDDBConnectors();
		List<Program> allProgs = mapper.scan(Program.class, new DynamoDBScanExpression());
		return allProgs;
	}		
	
	// Adding a program
	public Program addProgram(String programName, String deptID) throws Exception {
		Department dept = getAndValidateDept(deptID);
		initDDBConnectors();
		String programId = ServiceUtils.generateID(progPrefix);
		Program prog = new Program(programId, programName, deptID);
		// Store the program
		mapper.save(prog);
		
		// Store program in Dept
		List<String> programsInDept = dept.getPrograms();
		programsInDept.add(programName);
		dept.setPrograms(programsInDept);
		mapper.save(dept);
		// Return new program
		return prog;
	}
	
	// Getting one program
	public Program getProgram(String progID) throws Exception {
		initDDBConnectors();
		return getAndValidateProgram(progID);
	}
	
	// Deleting a program
	public Program deleteProgram(String progID) throws Exception {
		Program deletedProgDetails = getAndValidateProgram(progID);
		mapper.delete(deletedProgDetails);
		return deletedProgDetails;
	}
	
	// Updating Program info
	public Program updateProg(String progID, Program prog) throws Exception {
		Program progToUpdate = getAndValidateProgram(progID);
		if (prog.getProgramName() == null || prog.getProgramName().length() < 1) {
			throw new InvalidParameterException("Program name is invalid.");
		}
		progToUpdate.setProgramName(prog.getProgramName());
		mapper.save(progToUpdate);
		return progToUpdate;
	}
	
	private Program getAndValidateProgram(String programID) throws Exception {
		initDDBConnectors();
		Program prog = mapper.load(Program.class, programID);
		if(prog == null) {
			throw new ResourseNotFoundException("ProgramID:" + programID + "not found.");
		}
		return prog;
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
