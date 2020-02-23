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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.csye6225.fall2018.courseservice.Model.Course;
import com.csye6225.fall2018.courseservice.Service.CourseService;

//webapi/
@Path("/courses")
public class CourseResource {
	CourseService courseService = new CourseService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> getCourses() {
		return courseService.getAllCourses();
	}
	
	@GET
	@Path("/byProgram")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> getCoursesByProgram(@QueryParam("program") String program){
		if (program == null) {
			return courseService.getAllCourses();
		}
		return courseService.getCoursesByProgram(program);
	}
	
	// ... webapi/course/1 
	@GET
	@Path("/{courseID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Course getCourse(@PathParam("courseID") String courseID) throws Exception {
		System.out.println("course Resource: Looking for: " + courseID);
		return courseService.getCourse(courseID);
	}
	
	@DELETE
	@Path("/delete/{courseID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Course deleteCourse(@PathParam("courseID") String courseID) throws Exception {
		return courseService.deleteCourse(courseID);
	}
	
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Course addCourse(Course course) throws Exception {
		return courseService.addCourse(course.getCourseName(), course.getNoOfCredits(), course.getProgID());
	}
	
	@PUT
	@Path("/update/{courseID}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON) public Course updateCourse(@PathParam("courseID") String courseID, Course course) throws Exception {
		return courseService.updateCourseInformation(courseID, course);
	}

}
