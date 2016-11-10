package com.ttfc.dubbo.proxy.filter;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

@Provider
public class BasicAuthenticationFilter implements ContainerRequestFilter {
	public static final String AUTHENTICATION_HEADER = "Authorization";

	@Override
	public void filter(ContainerRequestContext containerRequest)
			throws WebApplicationException {

		String authCredentials = containerRequest
				.getHeaderString(AUTHENTICATION_HEADER);

		// better injected
		AuthenticationService authenticationService = new AuthenticationService();

		boolean authenticationStatus = authenticationService.basic(authCredentials);

		if (!authenticationStatus) {
			//throw new WebApplicationException(Status.UNAUTHORIZED);
			containerRequest.abortWith(
					Response.status(Response.Status.UNAUTHORIZED).entity("User cannot access the resource.").build());
		}

	}
}