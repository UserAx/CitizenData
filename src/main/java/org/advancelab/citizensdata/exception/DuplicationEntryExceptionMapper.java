package org.advancelab.citizensdata.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.advancelab.citizensdata.exception.DuplicationEntryException;
import org.advancelab.citizensdata.model.Error;

@Provider
public class DuplicationEntryExceptionMapper implements ExceptionMapper<DuplicationEntryException> {

	public Response toResponse(DuplicationEntryException e) {
		Error error = new Error(e.getMessage(), 422, "No available Documentatiton!");
		return Response.status(422).entity(error).build();
	}

}