package com.king.domain;

import java.util.List;

public class ProductVO {
	private int pno;
	private String title;
	private String content;
	private String writer;
	private double price;
	private String regdate;
	private String moddate;
	private int readcount;
	private List<FilesVO> filelist;
	private int cmtqty;
	
	public ProductVO() {}
	
	
	//상품등록
	public ProductVO(String title, String content, String writer, double price) {
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.price = price;
	}

	//상품목록
	public ProductVO(int pno, String title, String writer, double price, String moddate, int readcount) {
		this.pno = pno;
		this.title = title;
		this.writer = writer;
		this.price = price;
		this.moddate = moddate;
		this.readcount = readcount;
	}

	//상품수정
	public ProductVO(int pno, String title, String content, double price) {
		this.pno = pno;
		this.title = title;
		this.content = content;
		this.price = price;
	}


	public int getPno() {
		return pno;
	}


	public void setPno(int pno) {
		this.pno = pno;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getWriter() {
		return writer;
	}


	public void setWriter(String writer) {
		this.writer = writer;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public String getRegdate() {
		return regdate;
	}


	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}


	public String getModdate() {
		return moddate;
	}


	public void setModdate(String moddate) {
		this.moddate = moddate;
	}


	public int getReadcount() {
		return readcount;
	}


	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}


	public List<FilesVO> getFilelist() {
		return filelist;
	}


	public void setFilelist(List<FilesVO> filelist) {
		this.filelist = filelist;
	}


	public int getCmtqty() {
		return cmtqty;
	}


	public void setCmtqty(int cmtqty) {
		this.cmtqty = cmtqty;
	}
	
}
