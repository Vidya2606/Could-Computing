package com.csye6225.fall2018.courseservice.Resource;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.csye6225.fall2018.courseservice.Model.Professor;
import com.csye6225.fall2018.courseservice.Service.ProfessorService;

//.. /webapi/myresource
@Path("professors")
public class ProfessorResource {
ProfessorService profService = new ProfessorService();
	
	@GET
//	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Professor> getProfessors() {
		return profService.getAllProfessors();
	}
	
	@GET
	@Path("/byDept")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Professor> getProfessorsByDeparment(@QueryParam("department") String department) {
		if (department == null) {
			return profService.getAllProfessors();
		}
		return profService.getProfessorsByDepartment(department);
	}
	
	// ... webapi/professor/1 
	@GET
	@Path("/{professorId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Professor getProfessor(@PathParam("professorId") String profId) throws Exception {
		System.out.println("Professor Resource: Looking for: " + profId);
		return profService.getProfessor(profId);
	}
	
	@DELETE
	@Path("/delete/{professorId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Professor deleteProfessor(@PathParam("professorId") String profId) throws Exception {
		return profService.deleteProfessor(profId);
	}
	
	
	 @POST
	 @Path("/add")
	 @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON) 
	 public Professor addProfessor(Professor prof) throws Exception {
		 return profService.addProfessor(prof);
	}
	 
	 @PUT
	 @Path("/update/{profID}")
	 @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON) public Professor
	 updateProfessor(@PathParam("profID") String profID, Professor prof) throws Exception {
		 return profService.updateProfessorInformation(profID, prof);
	 }
	 
	 public void addProfessor(String firstName, String lastName, String
			 deptID, Date joiningDate) throws Exception { 
		 profService.addProfessor(firstName, lastName, deptID, joiningDate);
	 }
}
