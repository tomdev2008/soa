package com.ttfc.soa.dubbo.provider.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.ttfc.soa.dubbo.provider.service.impl.DemoServiceImpl;

@Test
@ContextConfiguration(locations = { "classpath:/META-INF/spring/*.xml" })
public class TestSpring extends AbstractTestNGSpringContextTests {

	@Autowired
	DemoServiceImpl demoService;

	@Test()
	void testSayHello() {

		String res = demoService.sayHello("maxkerrer");
		System.out.println(res);

		Assert.assertNotNull(res);
		Assert.assertTrue(res.startsWith("Hello maxkerrer"));


	}

}