package com.ttfc.dubbox.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Hello world!
 *
 */
public class RestApp {
	public static void main(String[] args) {
		String url = "http://localhost:8888/services/hello/max.json";
		System.out.println("Getting user via " + url);
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(url);
		Response response = target.request().get();
		try {
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed with HTTP error code : " + response.getStatus());
			}
			System.out.println("Successfully got result: " + response.readEntity(String.class));
		} finally {
			response.close();
			client.close();
		}

		client = ClientBuilder.newClient();
		target = client.target("http://localhost:8888/services/bran/delete.json");
		response = target.request().post(Entity.entity("{\"id\":\"4\"}", MediaType.APPLICATION_JSON_TYPE));
		try {
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed with HTTP error code : " + response.getStatus());
			}
			System.out.println("Successfully got result: " + response.readEntity(String.class));
		} finally {
			response.close();
			client.close();
		}

	}
}
