package com.max.proxy.api.services.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.rpc.RpcContext;
import com.max.proxy.api.services.ProxyBranchCompanyService;
import com.max.soa.api.domain.BranchCompany;
import com.max.soa.api.services.BranchCompanyService;
import com.max.soa.api.services.DemoService;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("bran")
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public class BranchCompanySerImpl implements ProxyBranchCompanyService {
	
	private BranchCompanyService branService;

	public void setBranService(BranchCompanyService branService) {
		this.branService = branService;
	}
	
	@Override
	@GET
	public BranchCompany getLast() {
		List<BranchCompany> lbrans = (List<BranchCompany>) branService.getBranchCompanys();
		BranchCompany fbran = (BranchCompany) lbrans.get(lbrans.size() - 1);
		return fbran;
	}
}
