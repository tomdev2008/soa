package com.ttfc.soa.dubbo.proxy.service;


import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.demo.user.User;
import com.alibaba.dubbo.demo.user.facade.RegistrationResult;
import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.ttfc.soa.dubbo.provider.domain.BranchCompany;
import com.ttfc.soa.dubbo.proxy.domain.WResult;


@Path("bran")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
public interface BranchCompanyServiceProxy {  
	@GET
	WResult getLast();
	
	@GET
	@Path("{id : \\d+}")
	WResult getBranchCompany(@PathParam("id") int id);
	
	@POST
	@Path("register")
	WResult registerBranchCompany(BranchCompany brand);
	
	@POST
	@Path("update")
	WResult updateBranchCompany(HashMap<String,String> data);
	
	@POST
	@Path("delete")
	WResult deleteBranchCompany(HashMap<String,String> data);
}  
