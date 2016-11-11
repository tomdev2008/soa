package com.ttfc.soa.dubbo.provider.service.impl;


import com.github.pagehelper.*;
import com.ttfc.soa.dubbo.provider.domain.BranchCompany;
import com.ttfc.soa.dubbo.provider.domain.PageResult;
import com.ttfc.soa.dubbo.provider.mapper.BranchCompanyMapper;
import com.ttfc.soa.dubbo.provider.service.BranchCompanyService;


import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.ttfc.soa.dubbo.provider.domain.PageBuilder;
@Service("braComService")
/*
 * 此处注解在Spring-Dubbo.xml文件中用到： <!--声明需要暴露的服务接口 -->
 * <dubbo:serviceinterface="com.ouc.service.BranchCompanyService"ref=
 * "braComService"/>
 */
public class BranchCompanySerImpl implements BranchCompanyService {

	@Resource
	BranchCompanyMapper branchCompanyMapper;

	public List<BranchCompany> getBranchCompanys() {
		return branchCompanyMapper.getBraComsFromDataBase();
	}

	public int saveBranchCompany(BranchCompany branchCompany) {
		return branchCompanyMapper.insertBranchCompany(branchCompany);
	}

	@Override
	public int deleteBranchCompany(BranchCompany branchCompany) {
		return branchCompanyMapper.deleteBranchCompany(branchCompany);
	}

	@Override
	public int updateBranchCompany(BranchCompany branchCompany) {
		return branchCompanyMapper.updateBranchCompany(branchCompany);
	}

	@Override
	public BranchCompany getBranchCompanyById(int id) {
		return branchCompanyMapper.selectBranchCompany(id);
	}
	
	@Override
	public PageResult<BranchCompany> selectBraComs() {	
		Page<BranchCompany> page = PageHelper.startPage(1, 2).setOrderBy("id asc").doSelectPage(new ISelect() {
            @Override
            public void doSelect() {
            	branchCompanyMapper.selectBraComs();
            }
        });        
		return PageResult.build(page).total(page.getTotal()).page(page.getPages()).currentPage(page.getPageNum());
	    /*PageResult<BranchCompany> pageResult = new PageResult<BranchCompany>();
	    pageResult.setCount(page.getTotal());
	    pageResult.setCurpage(page.getPageNum());
	    pageResult.setPages(page.getPages());
	    pageResult.setList(page);
		//return personPage.getRows();
		return pageResult;*/
	}
}
