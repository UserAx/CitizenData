package org.advancelab.citizensdata.security;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;

@Provider
public class BasicAuthFilter implements ContainerRequestFilter{

	//Header Key:
	private static final String Authorization_Header = "Authorization";
	
	//Our Basic Keyword variable:
	private static final String Authorization_Header_prefix = "Basic ";
	
	private static final String Secured_Url_Prefix = "secured";
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		//Only for those links which has word secured in them.
		if(requestContext.getUriInfo().getPath().contains(Secured_Url_Prefix)){
		//Access header metadata from request headers:
		List<String> authHeader = requestContext.getHeaders().get(Authorization_Header);
		
		if(authHeader != null && authHeader.size() > 0) {
			String authToken = authHeader.get(0);
			authToken = authToken.replaceFirst(Authorization_Header_prefix, "");
			String decodeString = Base64.decodeAsString(authToken);
			StringTokenizer tokenizer = new StringTokenizer(decodeString, ":");
			if(tokenizer.hasMoreTokens()) {
				String username = tokenizer.nextToken();
				String password = tokenizer.nextToken();
				if("user1".equalsIgnoreCase(username) && "MeroProject".equals(password)) {
					return;
				}
			}
		}
			Response unauthorizedStatus = Response.status(Response.Status.UNAUTHORIZED)
					.entity("Unauthorized User").build();
			requestContext.abortWith(unauthorizedStatus);
	}

	}
}
