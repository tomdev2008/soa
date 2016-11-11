package com.ttfc.soa.dubbo.provider.domain;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

import com.github.pagehelper.Page;

@SuppressWarnings("restriction")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PageResult<T>{
	

	@JsonProperty("count")
    @XmlElement(name = "count")
    @NotNull
	private long count;
	

	@JsonProperty("curpage")
    @XmlElement(name = "curpage")
    @NotNull
	private int curpage;
	

	@JsonProperty("pages")
    @XmlElement(name = "pages")
    @NotNull
	private int pages;

	@JsonProperty("list")
    @XmlElement(name = "list")
    @NotNull
	private Page<T> list;
	
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public int getCurpage() {
		return curpage;
	}
	public void setCurpage(int curpage) {
		this.curpage = curpage;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public Page<T> getList() {
		return list;
	}
	public void setList(Page<T> list) {
		this.list = list;
	}

	public PageResult<T> page(int pages){
		this.setPages(pages);;
		return this;
	}
	
	public PageResult<T> total(long totalRows){
		this.setCount(totalRows);
		return this;
	}
	
	public  PageResult<T> currentPage(int curr){
		this.setCurpage(curr);
		return this;
	}
	
	public static <T> PageResult<T> build(Page<T> page) {
		PageResult<T> p = new PageResult<T>();
		p.setList(page);		
		return p;
	}
}