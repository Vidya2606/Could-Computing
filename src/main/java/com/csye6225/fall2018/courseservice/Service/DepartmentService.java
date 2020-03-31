package com.csye6225.fall2018.courseservice.Service;

import java.util.HashMap;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.csye6225.fall2018.courseservice.DataBase.DepartmentDB;
import com.csye6225.fall2018.courseservice.Exceptions.InvalidParameterException;
import com.csye6225.fall2018.courseservice.Exceptions.ResourseNotFoundException;
import com.csye6225.fall2018.courseservice.Model.Department;
import com.csye6225.fall2018.courseservice.Model.DynamoDbConnector;


public class DepartmentService {
	static HashMap<String, Department> deptMap = DepartmentDB.getdeptDB();
	static DynamoDbConnector dynamoDb;
	DynamoDBMapper mapper; 
	static String deptPrefix = "dept";
	
	public DepartmentService() {
		dynamoDb = new DynamoDbConnector();
		DynamoDbConnector.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}
	
	//Getting list of all departments
	public List<Department> getallDept(){
		initDDBConnectors();
		List<Department> allDepts = mapper.scan(Department.class, new DynamoDBScanExpression());
		return allDepts;
	}
	
	//Adding a department
	public Department addDept(String deptName){
		initDDBConnectors();
		String deptId = ServiceUtils.generateID(deptPrefix);
		Department dept = new Department(deptId, deptName);
		mapper.save(dept);
		return dept;
	}
	
	//Getting one department
	public Department getDept(String deptID) throws Exception {
		initDDBConnectors();
	    return getAndValidateDept(deptID);
	}
	
	//Deleting a department
	public Department deleteDept(String deptID) throws Exception {
		Department deletedDeptDetails = getAndValidateDept(deptID);
		mapper.delete(deletedDeptDetails);
		return deletedDeptDetails;
	}

	//Updating department info
	public Department updateDept(String deptID, Department dept) throws Exception {
		Department deptToUpdate = getAndValidateDept(deptID);
		if (dept.getDeptName() == null || dept.getDeptName().length() < 1) {
			throw new InvalidParameterException("DepartmentName is invalid.");
		}
		deptToUpdate.setDeptName(dept.getDeptName());
		mapper.save(deptToUpdate);
		return deptToUpdate;
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
