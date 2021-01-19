package com.king.domain;


public class Paging {
	private int pageIdx; // 몇번째 페이지 인가?
	private int qty;
	private String range;
	private String keyword;
	
	public Paging() {
		this(1,10);
	}
	public Paging(int pageIdx, int qty) {
		this.pageIdx = pageIdx;
		this.qty = qty;
	}
	public int getPageIdx() {
		return pageIdx;
	}
	public void setPageIdx(int pageIdx) {
		this.pageIdx = pageIdx;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
}
