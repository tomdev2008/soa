package com.ttfc.soa.dubbo.provider.mapper;

import java.util.List;

import com.ttfc.soa.dubbo.provider.domain.BranchCompany;

public interface BranchCompanyMapper {

	int insertBranchCompany(BranchCompany branchCompany);
	int updateBranchCompany(BranchCompany branchCompany);
	int deleteBranchCompany(BranchCompany branchCompany);
	BranchCompany selectBranchCompany(int id);

	public List<BranchCompany> getBraComsFromDataBase();
}