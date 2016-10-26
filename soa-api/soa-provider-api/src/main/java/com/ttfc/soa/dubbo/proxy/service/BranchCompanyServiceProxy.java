package com.ttfc.soa.dubbo.proxy.service;


import java.util.HashMap;

import com.alibaba.dubbo.demo.user.User;
import com.alibaba.dubbo.demo.user.facade.RegistrationResult;
import com.ttfc.soa.dubbo.provider.domain.BranchCompany;

   
public interface BranchCompanyServiceProxy {  
	BranchCompany getLast();
	BranchCompany getBranchCompany(int id);
    RegistrationResult registerBranchCompany(BranchCompany brand);
    RegistrationResult updateBranchCompany(HashMap<String,String> data);
    RegistrationResult deleteBranchCompany(HashMap<String,String> data);
}  
