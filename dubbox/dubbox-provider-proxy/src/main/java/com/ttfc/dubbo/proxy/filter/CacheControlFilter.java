package com.ttfc.dubbo.proxy.filter;

import java.io.IOException;
import java.util.Map;


import javax.ws.rs.container.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import com.google.gson.Gson;

public class CacheControlFilter implements ContainerResponseFilter {

    public void filter(ContainerRequestContext req, ContainerResponseContext res) {
        if (req.getMethod().equals("GET")) {
        	MultivaluedMap<String, String> map = req.getHeaders();
        	Gson gson = new Gson();

        	System.out.println(map);
            res.getHeaders().add("Cache-Control", "someValue");
        }
    }
}

/*public class CacheControlFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		final SecurityContext securityContext = requestContext.getSecurityContext();
		if (securityContext == null || !securityContext.isUserInRole("privileged")) {
			requestContext.abortWith(
					Response.status(Response.Status.UNAUTHORIZED).entity("User cannot access the resource.").build());
		}
	}
}*/