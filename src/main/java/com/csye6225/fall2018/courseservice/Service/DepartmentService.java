package com.csye6225.fall2018.courseservice.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.csye6225.fall2018.courseservice.DataBase.DepartmentDB;
import com.csye6225.fall2018.courseservice.Exceptions.InvalidParameterException;
import com.csye6225.fall2018.courseservice.Exceptions.ResourseNotFoundException;
import com.csye6225.fall2018.courseservice.Model.Department;


public class DepartmentService {
	static HashMap<String, Department> deptMap = DepartmentDB.getdeptDB();
	static String deptPrefix = "dept";
	
	public DepartmentService() {
	}
	
	//Getting list of all departments
	public List<Department> getallDept(){
		ArrayList<Department> list = new ArrayList<>();
		for(Department dept: deptMap.values()) {
			list.add(dept);
		}
		return list;
	}
	
	//Adding a department
	public Department addDept(String deptName){
		String deptId = ServiceUtils.generateID(deptPrefix);
		Department dept = new Department(deptId, deptName);
		deptMap.put(deptId, dept);
		return dept;
	}
	
	//Getting one department
	public Department getDept(String deptID) throws Exception {
		validateDept(deptID);
		Department d = deptMap.get(deptID);
	    return d;
	}
	
	//Deleting a department
	public Department deleteDept(String deptID) throws Exception {
		validateDept(deptID);
		Department deletedDeptDetails = deptMap.get(deptID);
		deptMap.remove(deptID);
		return deletedDeptDetails;
	}

	//Updating department info
	public Department updateDept(String deptID, Department dept) throws Exception {
		validateDept(deptID);
		Department deptToUpdate = deptMap.get(deptID);
		if (dept.getDeptName() == null || dept.getDeptName().length() < 1) {
			throw new InvalidParameterException("DepartmentName is invalid.");
		}
		deptToUpdate.setDeptName(dept.getDeptName());
		deptMap.put(deptID, deptToUpdate);
		return deptToUpdate;
	}
	
	private void validateDept(String deptID) throws Exception {
		if(deptMap == null || !deptMap.containsKey(deptID)) {
			throw new ResourseNotFoundException("DeptID: " + deptID + " not found.");
		}
	}
}
