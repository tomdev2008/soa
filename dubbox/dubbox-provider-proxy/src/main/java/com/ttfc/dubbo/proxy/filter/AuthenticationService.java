package com.ttfc.dubbo.proxy.filter;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;
import java.util.StringTokenizer;

import com.google.common.base.Splitter;

public class AuthenticationService {
	public boolean basic(String authCredentials) {

		if (null == authCredentials)
			return false;
		// header value format will be "Basic encodedstring" for Basic
		// authentication. Example "Basic YWRtaW46YWRtaW4="
		final String encodedUserPassword = authCredentials.replaceFirst("Basic"
				+ " ", "");
		String usernameAndPassword = null;
		try {
			byte[] decodedBytes = Base64.getDecoder().decode(
					encodedUserPassword);
			usernameAndPassword = new String(decodedBytes, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		final StringTokenizer tokenizer = new StringTokenizer(
				usernameAndPassword, ":");
		final String username = tokenizer.nextToken();
		final String password = tokenizer.nextToken();

		// we have fixed the userid and password as admin
		// call some UserService/LDAP here
		boolean authenticationStatus = "admin".equals(username)	&& "admin".equals(password);
		return authenticationStatus;
	}
	
	public boolean digest(String authCredentials){
		if (null == authCredentials)
			return false;
		// header value format will be "Basic encodedstring" for Basic
		// authentication. Example "Basic YWRtaW46YWRtaW4="
		System.out.println(authCredentials);
		final String encodedUserPassword = authCredentials.replaceFirst("Digest"
				+ " ", "");
		String usernameAndPassword = null;
		
		Map<String, String> map= Splitter.on(",").withKeyValueSeparator("=").split(encodedUserPassword);
		map.forEach((k,v) -> System.out.println("key: "+k+" value:"+v));

		try {
			byte[] decodedBytes = Base64.getDecoder().decode(
					encodedUserPassword);
			usernameAndPassword = new String(decodedBytes, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		final StringTokenizer tokenizer = new StringTokenizer(
				usernameAndPassword, ":");
		final String username = tokenizer.nextToken();
		final String password = tokenizer.nextToken();

		// we have fixed the userid and password as admin
		// call some UserService/LDAP here
		boolean authenticationStatus = "admin".equals(username)	&& "admin".equals(password);
		return authenticationStatus;
	}
	
	public boolean token(String token){
		if (null == token)
			return false;
		
		if(!token.equals("admin")){
			return false;
		}
		return true;
	}
}