package org.advancelab.citizensdata.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.advancelab.citizensdata.exception.RecordNotFoundException;
import org.advancelab.citizensdata.model.Error;
import org.springframework.http.HttpStatus;

@Provider
public class RecordNotFoundExceptionMapper implements ExceptionMapper<RecordNotFoundException>{

	public Response toResponse(RecordNotFoundException e) {
		Error error = new Error(e.getMessage(), 404, "No available Documentatiton!");
		return Response.status(Status.NOT_FOUND).entity(error).build();
	}
	

}