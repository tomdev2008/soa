package com.ttfc.dubbo.proxy.filter;

import java.io.IOException;
import java.util.Map;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;


public class TockenAuthenticationFilter implements ContainerRequestFilter{
	public static final String TOKEN_HEADER = "_token";

    public void filter(ContainerRequestContext containerRequest) throws IOException {
    	 String token = containerRequest.getHeaderString(TOKEN_HEADER);     
    	 
    	 AuthenticationService authenticationService = new AuthenticationService();

 		 boolean authenticationStatus = authenticationService.token(token);

 		if (!authenticationStatus) {
 			//throw new WebApplicationException(Status.UNAUTHORIZED);
 			containerRequest.abortWith(
 					Response.status(Response.Status.UNAUTHORIZED).entity("User cannot access the resource.").build());
 		}

     }
}

