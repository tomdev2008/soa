package com.ttfc.soa.dubbo.provider.service;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.ttfc.soa.dubbo.provider.domain.BranchCompany;
import com.ttfc.soa.dubbo.provider.service.impl.BranchCompanySerImpl;

@Test
@ContextConfiguration(locations = { "classpath:/META-INF/spring/*.xml" })
public class TestBranch extends AbstractTestNGSpringContextTests {

	@Autowired
	BranchCompanySerImpl brand;

	@Test()
	void testGetBranchCompanys() {
		List<BranchCompany> list= brand.getBranchCompanys();
		AssertJUnit.assertEquals(list.size(), 0);
	}
	
	@BeforeTest
	public void beforeTest() {	
	    
	}		
	@AfterTest
	public void afterTest() {
				
	}	

}