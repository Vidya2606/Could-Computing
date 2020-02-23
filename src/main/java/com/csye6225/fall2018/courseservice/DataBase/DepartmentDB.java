package com.csye6225.fall2018.courseservice.DataBase;

import java.util.HashMap;

import com.csye6225.fall2018.courseservice.Model.Department;

public class DepartmentDB {
	private static HashMap<String, Department> departmentDB = new HashMap<> ();

	public static HashMap<String, Department> getdeptDB() {
		return departmentDB;
	}

}
