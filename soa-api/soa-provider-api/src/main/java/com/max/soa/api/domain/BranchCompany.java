package com.max.soa.api.domain;

import java.io.Serializable;

/* ----模块商分公司------------ 
 *  
 */
public class BranchCompany implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id; // 分公司ID
	private String name; // 分公司名称
	private String address; // 地址
	private String telNumber; // 联系方式
	private String moduleNo; // 模块商编号
	private Integer labId; // 实验室ID
	private String remark; // 备注

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	public String getModuleNo() {
		return moduleNo;
	}

	public void setModuleNo(String moduleNo) {
		this.moduleNo = moduleNo;
	}

	public Integer getLabId() {
		return labId;
	}

	public void setLabId(Integer labId) {
		this.labId = labId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
