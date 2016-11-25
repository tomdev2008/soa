package com.ttfc.soa.dubbo.provider.service;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ttfc.soa.dubbo.provider.service.impl.DemoServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/META-INF/spring/*.xml" })
public class TestDemo {
	@Autowired
	DemoServiceImpl demoService;

	@Test
	public void testSayHello() {
		String res = demoService.sayHello("maxkerrer");
		System.out.println(res);

		Assert.assertNotNull(res);
		Assert.assertTrue(res.startsWith("Hello maxkerrer"));
	}
}
