package com.ttfc.soa.dubbo.provider.service;


import java.util.List;

import com.ttfc.soa.dubbo.provider.domain.BranchCompany;
import com.ttfc.soa.dubbo.provider.domain.PageResult;

public interface BranchCompanyService {  
     
   public List<BranchCompany> getBranchCompanys();  
   public BranchCompany getBranchCompanyById(int id); 
   public int saveBranchCompany(BranchCompany branchCompany);  
   public int updateBranchCompany(BranchCompany branchCompany);  
   public int deleteBranchCompany(BranchCompany branchCompany);  
   public PageResult<BranchCompany> selectBraComs();
}  
