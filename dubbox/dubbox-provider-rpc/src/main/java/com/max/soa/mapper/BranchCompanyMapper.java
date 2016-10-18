package com.max.soa.mapper;

import java.util.List;

import com.max.soa.api.domain.BranchCompany;

public interface BranchCompanyMapper {

	int insertBranchCompany(BranchCompany branchCompany);
	int updateBranchCompany(BranchCompany branchCompany);
	int deleteBranchCompany(BranchCompany branchCompany);
	BranchCompany selectBranchCompany(int id);

	public List<BranchCompany> getBraComsFromDataBase();
}