package com.csye6225.fall2018.courseservice.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.csye6225.fall2018.courseservice.DataBase.DepartmentDB;
import com.csye6225.fall2018.courseservice.DataBase.ProgramDB;
import com.csye6225.fall2018.courseservice.Exceptions.InvalidParameterException;
import com.csye6225.fall2018.courseservice.Exceptions.ResourseNotFoundException;
import com.csye6225.fall2018.courseservice.Model.Department;
import com.csye6225.fall2018.courseservice.Model.Program;

public class ProgramService {
	HashMap<String, Program> programMap = ProgramDB.getProgramDB();
	HashMap<String, Department> deptMap = DepartmentDB.getdeptDB();
	static String progPrefix = "prog";
	
	public ProgramService() {
	}
	
	//Getting list of all programs
	//GET "..webapi/programs"
	public List<Program> getAllPrograms(){
		ArrayList<Program> list = new ArrayList<>();
		for(Program prog : programMap.values()) {
			list.add(prog);
		}
		return list;
	}		
	
	// Adding a program
	public Program addProgram(String programName, String deptID) throws Exception {
		validateDept(deptID);
		// Create a new program
		String programId = ServiceUtils.generateID(progPrefix);
		Program prog = new Program(programId, programName, deptID);
		// Store the program
		programMap.put(programId, prog);
		
		// Store program in Dept
		Department dept = deptMap.get(deptID);
		List<String> programsInDept = dept.getPrograms();
		programsInDept.add(programName);
		dept.setPrograms(programsInDept);
		deptMap.put(deptID, dept);
		
		// Return new program
		return prog;
	}
	
	// Getting one program
	public Program getProgram(String progID) throws Exception {
		validateProgram(progID);
		Program prog2 = programMap.get(progID);
		return prog2;
	}
	
	// Deleting a program
	public Program deleteProgram(String progID) throws Exception {
		validateProgram(progID);
		Program deletedProgDetails = programMap.get(progID);
		programMap.remove(progID);
		
		// Remove program from Dept
		if (deptMap.containsKey(deletedProgDetails.getDeptID())) {
			Department dept = deptMap.get(deletedProgDetails.getDeptID());
			List <String> programs = dept.getPrograms();
			programs.remove(progID);
			dept.setPrograms(programs);
			deptMap.put(deletedProgDetails.getDeptID(), dept);
		}
		
		return deletedProgDetails;
	}
	
	// Updating Program info
	public Program updateProg(String progID, Program prog) throws Exception {
		validateProgram(progID);
		if (prog.getProgramName() == null || prog.getProgramName().length() < 1) {
			throw new InvalidParameterException("Program name is invalid.");
		}
		programMap.put(progID, prog);
		return prog;
	}
	
	private void validateProgram(String programID) throws Exception {
		if (programMap == null || !programMap.containsKey(programID)) {
			throw new ResourseNotFoundException("programID: " + programID + " was not found.");
		}
	}
	
	private void validateDept(String deptID) throws Exception {
		if(deptMap == null || !deptMap.containsKey(deptID)) {
			throw new ResourseNotFoundException("DeptID: " + deptID + " not found.");
		}
	}
}
