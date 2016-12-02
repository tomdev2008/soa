package com.ttfc.soa.dubbo.proxy.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.ttfc.soa.dubbo.provider.domain.BranchCompany;
import com.ttfc.soa.dubbo.provider.domain.PageResult;
import com.ttfc.soa.dubbo.provider.service.BranchCompanyService;
import com.ttfc.soa.dubbo.proxy.domain.WResponse;
import com.ttfc.soa.dubbo.proxy.domain.WResult;
import com.ttfc.soa.dubbo.proxy.service.BranchCompanyServiceProxy;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Service("braComServiceProxy")
public class BranchCompanySerImplProxy implements BranchCompanyServiceProxy {

	@Autowired
	private BranchCompanyService branService;

	public void setBranService(BranchCompanyService branService) {
		this.branService = branService;
	}

	@Override
	public WResult getLast() {
		PageResult<BranchCompany> lbrans = (PageResult<BranchCompany>) branService.selectBraComs();
		return WResponse.success(WResponse.Action.ACCEPTED).entity(lbrans).build();
	}

	@Override
	public WResult getBranchCompany(int id/* , @Context HttpServletRequest request */) {
		BranchCompany fbran = (BranchCompany) branService.getBranchCompanyById(id);
		return WResponse.success(WResponse.Action.ACCEPTED).entity(fbran).build();
	}

	@Override	
	public WResult registerBranchCompany(BranchCompany brand) {
		int res = branService.saveBranchCompany(brand);		
		return WResponse.success(WResponse.Action.ACCEPTED).entity(Long.valueOf(res)).build();
	}

	@Override	
	public WResult updateBranchCompany(HashMap<String, String> data) {
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
		return WResponse.success(WResponse.Action.ACCEPTED).entity(Long.valueOf(res)).build();
	}

	@Override	
	public WResult deleteBranchCompany(HashMap<String, String> data) {
		int id = Integer.valueOf(data.get("id"));
		BranchCompany brand = branService.getBranchCompanyById(id);
		int res = branService.deleteBranchCompany(brand);
		return WResponse.success(WResponse.Action.ACCEPTED).entity(Long.valueOf(res)).build();
	}

	
}

