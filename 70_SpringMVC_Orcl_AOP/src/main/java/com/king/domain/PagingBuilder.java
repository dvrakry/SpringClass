package com.king.domain;


public class PagingBuilder {
	private int totalCount; // 총 글의 갯수
	private int firstPageIdx;// 페이지네이션의 가장 앞번호
	private int lastPageIdx;// 페이지 네이션의 가장 끝번호
	private boolean prev, next;//이전, 다움 페이지네이션으로 이동 버튼 생성 여부
	private Paging pg;//여기 Paging이 도메인에 만든 기준Paging(class) , 페이징 기준 객체
	
	public PagingBuilder() {}
	
	public PagingBuilder(int totalCount, Paging pg) {
		this.totalCount = totalCount;
		this.pg = pg;
		
		// 17(내가 클릭한 페이징번호) 17/10.0 -> 1.7 -> ceil(1.7) -> 2 -> 2*10 -> 20 이게 lastPageIdx 
		this.lastPageIdx = (int)(Math.ceil(pg.getPageIdx()/10.0))*10;
		this.firstPageIdx = this.lastPageIdx-9;
		
		//DB에서 가져온 총 글의 갯수를 114 -> 114*1.0 -> 114.0/10 -> 11.4 -> ceil(11.4) -> 12 이게 리얼라스트페이지인덱스
		int realLastPageIdx = (int)(Math.ceil((totalCount*1.0)/pg.getQty()));
		if(realLastPageIdx <= this.lastPageIdx) {
			this.lastPageIdx = realLastPageIdx;
		}
		this.prev = firstPageIdx > 1;
		this.next = this.lastPageIdx < realLastPageIdx;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getFirstPageIdx() {
		return firstPageIdx;
	}

	public void setFirstPageIdx(int firstPageIdx) {
		this.firstPageIdx = firstPageIdx;
	}

	public int getLastPageIdx() {
		return lastPageIdx;
	}

	public void setLastPageIdx(int lastPageIdx) {
		this.lastPageIdx = lastPageIdx;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public Paging getPg() {
		return pg;
	}

	public void setPg(Paging pg) {
		this.pg = pg;
	}
	
}
