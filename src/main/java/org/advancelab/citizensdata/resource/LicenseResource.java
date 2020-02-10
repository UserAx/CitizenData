package org.advancelab.citizensdata.resource;

import java.net.URI;
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
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import org.advancelab.citizensdata.model.License;
import org.advancelab.citizensdata.resourcebeans.FilterBeans;
import org.advancelab.citizensdata.service.LicenseService;

@Path("licensedata")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LicenseResource {
	
	private LicenseService licenseService = new LicenseService();
	
	@GET
	public List<License> getLicenses(@BeanParam FilterBeans filterBeans) {
		if(filterBeans.getBirthYear() > 0) {
			return licenseService.getLicenses(filterBeans.getCatagory());
		} else if(filterBeans.getEnd() > 0 && filterBeans.getStart() > 0) {
			return licenseService.getLicenses(filterBeans.getStart(), filterBeans.getEnd());
		}
		return licenseService.getLicenses();
	}
	
	
	@GET
	@Path("/{licenseId}")
	public License getLicense(@PathParam("licenseId") String licenseId) {
		return licenseService.getLicense(licenseId);
	}
	
	@POST
	public Response addLicense(License license, @Context UriInfo uriInfo) {//Response here.
		licenseService.addLicense(license);
		String message = "Here is the link for this entry is in the Headers.";
		URI uri = uriInfo.getAbsolutePathBuilder().path(license.getLicenseId()).build();
		return Response.created(uri).entity(message).build();
	}
	
	@PUT
	@Path("/secured/{licenseId}")//Response
	public Response updateLicense(@PathParam("licenseId") String licenseId, License license) {
		licenseService.updateLicense(licenseId, license);
		String message = "Update Successful!";
		return Response.status(Status.OK).entity(message).build();
	}
	
	@DELETE
	@Path("/secured/{licenseId}")//Response
	public Response deleteLicense(@PathParam("licenseId") String licenseId) {
		licenseService.deleteLicense(licenseId);
		String message = "Delete Successful!";
		return Response.status(Status.OK).entity(message).build();
	}
}
