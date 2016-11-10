package com.ttfc.soa.dubbo.filter;

import java.util.ArrayList;

public class IpWhiteList {
	private Boolean enable = true;

	private ArrayList<String> ips = new ArrayList<String>();

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public ArrayList<String> getIps() {
		return ips;
	}

	public void setIps(ArrayList<String> ips) {
		this.ips = ips;
	}
	
	public Boolean isEnabled(){
		return this.enable;
	} 

	public ArrayList<String> getAllowedIps(){
		return this.ips;
	}
}
