package com.king.domain;

import java.util.List;

public class CommentDTO {
	private int total;
	private List<CommentVO> clist;
	// 이게 빌더라고 생각하면 편함 , lastIdx 이런거 없는이유는 자바스크립트로 해야되기때문임. 그래서 토탈과 리스트값만 가지고 자바스크립트에서 계산시키는거임
	public CommentDTO() {}
	public CommentDTO(int total, List<CommentVO> clist) {
		this.total = total;
		this.clist = clist;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<CommentVO> getClist() {
		return clist;
	}
	public void setClist(List<CommentVO> clist) {
		this.clist = clist;
	}
	
}
