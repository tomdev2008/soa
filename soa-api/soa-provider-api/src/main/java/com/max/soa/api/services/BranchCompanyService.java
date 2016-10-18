package com.max.soa.api.services;


import java.util.List;

import com.max.soa.api.domain.BranchCompany;  
   
public interface BranchCompanyService {  
     
   public List<BranchCompany> getBranchCompanys();  
   public BranchCompany getBranchCompanyById(int id); 
   public int saveBranchCompany(BranchCompany branchCompany);  
   public int updateBranchCompany(BranchCompany branchCompany);  
   public int deleteBranchCompany(BranchCompany branchCompany);  
}  
