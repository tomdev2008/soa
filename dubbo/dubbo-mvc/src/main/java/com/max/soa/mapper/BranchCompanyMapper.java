package com.max.soa.mapper;


import java.util.List;

import com.ttfc.soa.dubbo.provider.domain.BranchCompany;  
   
public interface BranchCompanyMapper {  
    
	int insertBranchCompany(BranchCompany branchCompany);  
        
      public List<BranchCompany> getBraComsFromDataBase();  
}  