package com.csye6225.fall2018.courseservice.Resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.csye6225.fall2018.courseservice.Model.Program;
import com.csye6225.fall2018.courseservice.Service.ProgramService;

@Path("programs")
public class ProgramResource {
	ProgramService progService = new ProgramService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Program> getAllPrograms(String progID) {
		return progService.getAllPrograms();
	}
	
	@GET
	@Path("/{progID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Program getPrpg(@PathParam("progID") String progID) throws Exception {
		System.out.println("Program Resource: Looking for: " + progID);
		return progService.getProgram(progID);
	}
	
	@DELETE
	@Path("/delete/{progID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Program deleteProg(@PathParam("progID") String progID) throws Exception {
		return progService.deleteProgram(progID);
	}
	
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Program addProg(Program prog) throws Exception {
		return progService.addProgram(prog.getProgramName(), prog.getDeptID());
	}	
	
	@PUT
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON) 
	public Program updateProg(Program prog) throws Exception {
		 return progService.updateProg(prog.getProgramID(), prog);
	 }
}
