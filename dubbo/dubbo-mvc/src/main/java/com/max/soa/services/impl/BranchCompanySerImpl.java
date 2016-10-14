package com.max.soa.services.impl;

import com.max.soa.api.domain.BranchCompany;
import com.max.soa.api.services.BranchCompanyService;
import com.max.soa.mapper.BranchCompanyMapper;

import java.util.List;  
   
import javax.annotation.Resource;  
import org.springframework.stereotype.Service;  
   
@Service("braComService")  
/* 此处注解在Spring-Dubbo.xml文件中用到： 
<!--声明需要暴露的服务接口 --> 
   <dubbo:serviceinterface="com.ouc.service.BranchCompanyService"ref="braComService"/> 
*/     
public class BranchCompanySerImpl implements BranchCompanyService{  
    
   @Resource  BranchCompanyMapper branchCompanyMapper;  
   
   public List<BranchCompany> getBranchCompanys()  
   {  
      return branchCompanyMapper.getBraComsFromDataBase();  
   }  
    
   public int saveBranchCompany(BranchCompany branchCompany)  
   {  
      return branchCompanyMapper.insertBranchCompany(branchCompany);  
   }  
}  
