package com.csye6225.fall2018.courseservice.Exceptions;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidParameterException extends Exception implements ExceptionMapper<InvalidParameterException> {
	private static final long serialVersionUID = 1L;
	
	public InvalidParameterException() {
		super("One or more parameters were invalid.");
	}
	
	public InvalidParameterException(String message) {
		super(message);
	}

	@Override
	public Response toResponse(InvalidParameterException exception) {
		// Returns 400
		return Response.status(400).entity(exception.getMessage()).type(MediaType.APPLICATION_JSON).build();
	}
}
