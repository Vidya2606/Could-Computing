package com.csye6225.fall2018.courseservice.DataBase;

import java.util.HashMap;

import com.csye6225.fall2018.courseservice.Model.Professor;

public class DataBaseClass {
	private static HashMap<String, Professor> professorDB = new HashMap<> ();

	public static HashMap<String, Professor> getProfessorDB() {
		return professorDB;
	}

}
