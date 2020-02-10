package org.advancelab.citizensdata.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.advancelab.citizensdata.exception.EmptyRecordException;
import org.advancelab.citizensdata.model.Error;

@Provider
public class EmptyRecordExceptionMapper implements ExceptionMapper<EmptyRecordException>{

	public Response toResponse(EmptyRecordException e) {
		Error error = new Error(e.getMessage(), 404, "No available Documentatiton!");
		return Response.status(Status.NOT_FOUND).entity(error).build();
	}

}
