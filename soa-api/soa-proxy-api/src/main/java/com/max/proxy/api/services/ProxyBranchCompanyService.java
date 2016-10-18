package com.max.proxy.api.services;


import java.util.HashMap;

import com.alibaba.dubbo.demo.user.User;
import com.alibaba.dubbo.demo.user.facade.RegistrationResult;
import com.max.soa.api.domain.BranchCompany;

   
public interface ProxyBranchCompanyService {  
	BranchCompany getLast();
	BranchCompany getBranchCompany(int id);
    RegistrationResult registerBranchCompany(BranchCompany brand);
    RegistrationResult updateBranchCompany(HashMap<String,String> data);
    RegistrationResult deleteBranchCompany(HashMap<String,String> data);
}  
