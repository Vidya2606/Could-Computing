package com.csye6225.fall2018.courseservice.Resource;

import java.util.List;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.csye6225.fall2018.courseservice.Model.Student;
import com.csye6225.fall2018.courseservice.Service.StudentService;

//.. /webapi/myresource
@Path("students")
public class StudentResource {
	StudentService studService = new StudentService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getStudents(){
		return studService.getAllStudents();
	}
	
	@GET
	@Path("/byProgram")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getStudentByProgram(@QueryParam("program") String program) {
		if (program == null) {
			return studService.getAllStudents();
		}
		return studService.getStudentByProgram(program);
	}
	
	// ... webapi/student/1 
	@GET
	@Path("/{studentID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student getStudents(@PathParam("studentID")String studentID) throws Exception {
		System.out.println("Student resource: Looking for:" + studentID);
		return studService.getStudent(studentID);
	}
	
	@DELETE
	@Path("/delete/{studentID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student deleteStudent(@PathParam("studentID") String studID) throws Exception {
		return studService.deleteStudent(studID);
	}
	
	@POST 
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Student addStudent(Student stud) throws Exception {
		return studService.addStudent(stud);
	}
	
	@PUT
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON) 
	public Student updateStudent(Student stud) throws Exception {
		return studService.updateStudentInfo(stud.getStudentID(), stud); 
	}
}
