package com.ttfc.dubbo.proxy.filter;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.*;
import javax.ws.rs.core.*;

import org.apache.commons.codec.digest.DigestUtils;

import com.ttfc.dubbo.proxy.filter.digest.Header;
import com.ttfc.dubbo.proxy.filter.digest.Nonce;

public class DigestAuthenticationFilter implements ContainerRequestFilter {
	public static final String AUTHENTICATION_HEADER = "Authorization";

	@Override
	public void filter(ContainerRequestContext containerRequest) throws WebApplicationException {

		String digestUserPass = containerRequest.getHeaderString(AUTHENTICATION_HEADER);
		if (null != digestUserPass) {
			final String encodedUserPassword = digestUserPass.replaceFirst("Digest" + " ", "");
			Header header = new Header(encodedUserPassword, containerRequest.getMethod());
			//System.out.println("================" + header.getNounceCounter());
			if (Nonce.IsValid(header.getNonce(), header.getNounceCounter())) {
				// Just assuming password is same as username for the purpose of
				// illustration
				String password = header.getUserName();

				String ha1 = DigestUtils
						.md5Hex(String.format("%s:%s:%s", header.getUserName(), header.getRealm(), password));

				String ha2 = DigestUtils.md5Hex(String.format("%s:%s", header.getMethod(), header.getUri()));

				String computedResponse = DigestUtils.md5Hex(String.format("%s:%s:%s:%s:%s:%s", ha1, header.getNonce(),
						header.getNounceCounter(), header.getCnonce(), "auth", ha2));
				System.out.println(computedResponse);
				System.out.println(header.getResponse());
				if (!computedResponse.equals(header.getResponse())) {
					containerRequest.abortWith(Response.status(Response.Status.UNAUTHORIZED)
							.header("WWW-Authenticate", "Digest " + Header.ErrorauthorizedResponseHeader(header.getNonce()).toString())
							.entity("User1 cannot access the resource.").build());
				}
				System.out.println("sucess digest");
			} else {
				containerRequest.abortWith(Response.status(Response.Status.UNAUTHORIZED)
						.header("WWW-Authenticate", "Digest " + Header.ErrorauthorizedResponseHeader(header.getNonce()).toString())
						.entity("User2 cannot access the resource.").build());
			}

		} else {
			containerRequest.abortWith(Response.status(Response.Status.UNAUTHORIZED)
					.header("WWW-Authenticate", "Digest " + Header.UnauthorizedResponseHeader().toString())
					.entity("User cannot access the resource.").build());
		}
	}

}
