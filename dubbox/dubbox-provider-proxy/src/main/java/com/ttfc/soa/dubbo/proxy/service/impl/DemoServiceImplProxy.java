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
package com.ttfc.soa.dubbo.proxy.service.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.ttfc.soa.dubbo.provider.service.DemoService;
import com.ttfc.soa.dubbo.proxy.domain.WResponse;
import com.ttfc.soa.dubbo.proxy.domain.WResult;
import com.ttfc.soa.dubbo.proxy.service.DemoServiceProxy;


@Service("demoServiceProxy")
public class DemoServiceImplProxy implements DemoServiceProxy {

	@Autowired
	private DemoService demoService;

	public void setDemoService(DemoService demoService) {
		this.demoService = demoService;
	}

	@Override
	public WResult getHello(String name) {
		String result = demoService.sayHello(name);
        
		return WResponse.success(WResponse.Action.ACCEPTED).entity(result+":proxy").build();
		//return WResponse.fail(WResponse.Action.BAD_REQUEST).msg("erro happend").build();
	}

}