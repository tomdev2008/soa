package com.max.proxy.api.services.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.demo.user.facade.RegistrationResult;
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
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
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

	@Override
	@GET
	@Path("{id : \\d+}")
	public BranchCompany getBranchCompany(
			@PathParam("id") int id/* , @Context HttpServletRequest request */) {
		BranchCompany fbran = (BranchCompany) branService.getBranchCompanyById(id);
		return fbran;
	}

	@Override
	@POST
	@Path("register")
	public RegistrationResult registerBranchCompany(BranchCompany brand) {
		int res = branService.saveBranchCompany(brand);		
		return new RegistrationResult(Long.valueOf(res));
	}

	@Override
	@POST
	@Path("update")
	public RegistrationResult updateBranchCompany(HashMap<String, String> data) {
		int id = Integer.valueOf(data.get("id"));
		BranchCompany brandduty = (BranchCompany) branService.getBranchCompanyById(id);		
		
		String methodName = "";
		for (String name : data.keySet()) {
			if (name.equals("id")) {
				continue;
			}

			methodName = "set" + StringUtils.capitalize(name);
          
			/**
			 * if(name.equals("sfasdfa")){ Method setNameMethod =
			 * aClass.getClass().getMethod(methodName, int.class); }
			 */
			Method setNameMethod;
			try {
				setNameMethod = brandduty.getClass().getMethod(methodName, new Class[] {String.class});		
		
				setNameMethod.invoke(brandduty, data.get(name));
			} catch (NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}

	
		int res = branService.updateBranchCompany(brandduty);
		return new RegistrationResult(Long.valueOf(res));
	}

	@Override
	@POST
	@Path("delete")
	public RegistrationResult deleteBranchCompany(HashMap<String, String> data) {
		int id = Integer.valueOf(data.get("id"));
		BranchCompany brand = branService.getBranchCompanyById(id);
		int res = branService.deleteBranchCompany(brand);
		return new RegistrationResult(Long.valueOf(res));
	}

}
