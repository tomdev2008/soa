package com.ttfc.dubbox.client;

import java.util.HashMap;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.dubbo.demo.bid.BidService;
import com.alibaba.dubbo.demo.user.facade.AnotherUserRestService;
import com.ttfc.soa.dubbo.proxy.domain.WResult;
import com.ttfc.soa.dubbo.proxy.service.BranchCompanyServiceProxy;
import com.ttfc.soa.dubbo.proxy.service.DemoServiceProxy;

/**
 * Hello world!
 *
 */
public class DubboxApp {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/spring/dubbo-ttfc.xml");
		BranchCompanyServiceProxy brand = (BranchCompanyServiceProxy) context.getBean("braComServiceProxy");
		AnotherUserRestService demo2 = (AnotherUserRestService) context.getBean("anotherUserRestService");
		DemoServiceProxy demo = (DemoServiceProxy) context.getBean("demoServiceProxy");
		WResult result =  demo.getHello("max");
		
		HashMap<String,String> data = new HashMap<String,String>();
		data.put("id", "1");
		WResult result2 = brand.deleteBranchCompany(data);
		System.out.println(result.getEntity());
		System.out.println(result2.getEntity());
	}
}
