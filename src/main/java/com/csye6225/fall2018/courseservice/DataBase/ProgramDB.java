package com.csye6225.fall2018.courseservice.DataBase;

import java.util.HashMap;

import com.csye6225.fall2018.courseservice.Model.Program;

public class ProgramDB {
	private static HashMap<String, Program> programDB = new HashMap<>();
	
	public static HashMap<String, Program> getProgramDB(){
		return programDB;
	}

}
