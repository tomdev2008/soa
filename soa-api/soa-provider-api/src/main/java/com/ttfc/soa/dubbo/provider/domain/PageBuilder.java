package com.ttfc.soa.dubbo.provider.domain;

import com.github.pagehelper.Page;

public class PageBuilder {
	private long _count;
	private int _curpage;
	private int _pages;
	
	public static PageBuilder Factory(){
		PageBuilder builder = new PageBuilder();
		return builder;
	}
	
	public PageBuilder page(int pages){
		_pages = pages;
		return this;
	}
	
	public PageBuilder total(long totalRows){
		_count = totalRows;
		return this;
	}
	
	public  PageBuilder currentPage(int curr){
		_curpage = curr;
		return this;
	}
	
	public  <T> PageResult<T> build(Page<T> page) {
		PageResult<T> p = new PageResult<T>();
		p.setCount(_count);
		p.setPages(_pages);
		p.setCurpage(_curpage);
		p.setList(page);		
		return p;
	}
}
