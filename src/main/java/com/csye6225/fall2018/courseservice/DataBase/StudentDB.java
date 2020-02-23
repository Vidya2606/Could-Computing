package com.csye6225.fall2018.courseservice.DataBase;

import java.util.HashMap;

import com.csye6225.fall2018.courseservice.Model.Student;

public class StudentDB {
	private static HashMap<String, Student> studentDB = new HashMap<>	();
	
	public static HashMap<String, Student> getStudentDB(){
		return studentDB;
	}
}
