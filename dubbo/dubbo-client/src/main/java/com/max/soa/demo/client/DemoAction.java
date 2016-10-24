/*
 * Copyright 1999-2011 Alibaba Group.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.max.soa.demo.client;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;


import com.ttfc.soa.dubbo.provider.domain.BranchCompany;
import com.ttfc.soa.dubbo.provider.service.BranchCompanyService;
import com.ttfc.soa.dubbo.provider.service.DemoService;


public class DemoAction {

	private DemoService demoService;
	private BranchCompanyService branService;

	public void setDemoService(DemoService demoService) {
		this.demoService = demoService;
	}

	public void setBranService(BranchCompanyService branService) {
		this.branService = branService;
	}

	public void start() throws Exception {
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			try {
				String hello = demoService.sayHello("world" + i);
				System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] " + hello);

				if (i % 10 == 0) {
					List<BranchCompany> lbrans = (List<BranchCompany>) branService.getBranchCompanys();
					BranchCompany fbran = (BranchCompany) lbrans.get(lbrans.size() - 1);
					System.out.println(fbran.getId() + ": " + fbran.getLabId() + "-" + fbran.getRemark());
				}

				if (i % 8 == 0) {
					String uuid = UUID.randomUUID().toString();
					BranchCompany braCom = new BranchCompany();
					braCom.setName("Hai");
					braCom.setLabId(i);
					braCom.setAddress("SSdsg");
					braCom.setModuleNo("SSS2000");
					braCom.setTelNumber("125604");
					braCom.setRemark(uuid);
					branService.saveBranchCompany(braCom);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			Thread.sleep(2000);
		}
	}

}