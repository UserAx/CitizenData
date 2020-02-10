package org.advancelab.citizensdata.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.advancelab.citizensdata.model.Error;

@Provider
public class InvalidQueryArgumentExceptionMapper implements ExceptionMapper<InvalidQueryArgumentException> {

	@Override
	public Response toResponse(InvalidQueryArgumentException e) {
		Error error = new Error(e.getMessage(), 400, "No documentation Available");
		return Response.status(Status.BAD_REQUEST).entity(error).build();
	}

}
