package org.advancelab.citizensdata.resource;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.advancelab.citizensdata.model.Citizen;
import org.advancelab.citizensdata.model.License;
import org.advancelab.citizensdata.model.Links;
import org.advancelab.citizensdata.resourcebeans.FilterBeans;
import org.advancelab.citizensdata.service.CitizenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@Path("citizensdata")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CitizenResource {
	
	private CitizenService citizenService = new CitizenService();
	
	@GET
	public List<Citizen> getCitizens(@BeanParam FilterBeans filterBeans) {
		if(filterBeans.getBirthYear() > 0) {
			return citizenService.getCitizens(filterBeans.getBirthYear());
		} else if(filterBeans.getEnd() > 0 && filterBeans.getStart() > 0) {
			return citizenService.getCitizens(filterBeans.getStart(), filterBeans.getEnd());
		}
		return citizenService.getCitizens();
	}
	
	@GET
	@Path("/{cardId}")
	public Citizen getCitizen(@PathParam("cardId") String cardId, @Context UriInfo uriInfo) {
		Citizen citizen = citizenService.getCitizen(cardId);
		List<Links> links = setLinks(uriInfo, citizen);
		citizen.setLinks(links);
		return citizen;
	}
	
	@POST
	public Response addCitizen(Citizen citizen, @Context UriInfo uriInfo) {//Response here.
		citizenService.addCitizen(citizen);
		String message = "Here is the link for this entry is in the Headers.";
		URI uri = uriInfo.getAbsolutePathBuilder().path(citizen.getCardId()).build();
		return Response.created(uri).entity(message).build();
	}
	
	@PUT
	@Path("/secured/{cardId}")//Response
	public Response updateCitizen(@PathParam("cardId") String cardId, Citizen citizen) {
		citizenService.updateCitizen(cardId, citizen);
		String message = "Update Successful!";
		return Response.status(Status.OK).entity(message).build();
	}
	
	@DELETE
	@Path("/secured/{cardId}")//Response
	public Response deleteCitizen(@PathParam("cardId") String cardId) {
		citizenService.deleteCitizen(cardId);
		String message = "Delete Successful!";
		return Response.status(Status.OK).entity(message).build();
	}
	
	public List<Links> setLinks(UriInfo uriInfo, Citizen citizen) {
		String selfUrl = uriInfo.getBaseUriBuilder().path(CitizenResource.class).path(citizen.getCardId().toString()).toString();
		String licenseUrl = uriInfo.getBaseUriBuilder().path(LicenseResource.class).path(citizen.getLicense().getLicenseId().toString()).toString();
		Links selfLink= new Links("Self", selfUrl);
		Links licenseLink= new Links("License", licenseUrl);
		List<Links> links = Arrays.asList(selfLink, licenseLink);
		return links;
	}
}
