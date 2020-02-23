package com.csye6225.fall2018.courseservice.DataBase;

import java.util.HashMap;

import com.csye6225.fall2018.courseservice.Model.Course;

public class CourseDB {
	private static HashMap<String, Course> courseDB = new HashMap<>();
	
	public static HashMap<String, Course> getCourseDB(){
		return courseDB;
	}

}
