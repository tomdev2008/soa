/*
 * Copyright 1999-2011 Alibaba Group.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.max.proxy.api.services.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;

import com.max.proxy.api.services.Hello;
import com.max.proxy.api.services.ProxyDemoService;
import com.max.soa.api.services.BranchCompanyService;
import com.max.soa.api.services.DemoService;

@Path("hello")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
public class DemoServiceImpl implements ProxyDemoService {

	private DemoService demoService;

	public void setDemoService(DemoService demoService) {
		this.demoService = demoService;
	}

	@Override
	@GET
	@Path("{name}")
	public Hello getHello(@PathParam("name") String name) {
		System.out.println("starting............................" + name);
		String result = demoService.sayHello(name);
		System.out.println(result);
		return new Hello("ok", result);
	}

}