package com.ttfc.soa.dubbo.proxy.service;


import java.util.HashMap;

import com.alibaba.dubbo.demo.user.User;
import com.alibaba.dubbo.demo.user.facade.RegistrationResult;
import com.ttfc.soa.dubbo.provider.domain.BranchCompany;
import com.ttfc.soa.dubbo.proxy.domain.WResult;

   
public interface BranchCompanyServiceProxy {  
	WResult getLast();
	WResult getBranchCompany(int id);
	WResult registerBranchCompany(BranchCompany brand);
	WResult updateBranchCompany(HashMap<String,String> data);
	WResult deleteBranchCompany(HashMap<String,String> data);
}  
