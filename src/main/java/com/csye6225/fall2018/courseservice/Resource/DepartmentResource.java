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

import com.csye6225.fall2018.courseservice.Model.Department;
import com.csye6225.fall2018.courseservice.Service.DepartmentService;

@Path("departments")
public class DepartmentResource {
	DepartmentService deptService = new DepartmentService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Department> getdept() {
		return deptService.getallDept();
	}
	
	@GET
	@Path("/{deptID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Department getDept(@PathParam("deptID") String deptID) throws Exception {
		System.out.println("Department Resource: Looking for: " + deptID);
		return deptService.getDept(deptID);
	}
	
	@DELETE
	@Path("/delete/{deptID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Department deleteDept(@PathParam("deptID") String deptID) throws Exception {
		return deptService.deleteDept(deptID);
	}
	
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Department addDept(Department dept) { 
		return deptService.addDept(dept.getDeptName());
	}
	
	@PUT
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON) 
	public Department updateDept(Department dept) throws Exception {
		 return deptService.updateDept(dept.getDeptID(), dept);
	 }
}
