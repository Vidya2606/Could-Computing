package com.csye6225.fall2018.courseservice.Exceptions;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ResourseNotFoundException  extends Exception implements ExceptionMapper<ResourseNotFoundException> {

    private static final long serialVersionUID = 3L;
	
	public ResourseNotFoundException() {
		super("One or more resources in the request were not found.");
	}
	
	public ResourseNotFoundException(String message) {
		super(message);
	}
	
	@Override
	public Response toResponse(ResourseNotFoundException exception) {
		// Returns 404
		return Response.status(404).entity(exception.getMessage()).type(MediaType.APPLICATION_JSON).build();
	}
}
