package com.king.service;

import com.king.domain.CommentDTO;
import com.king.domain.CommentVO;
import com.king.domain.Paging;

public interface CommentService {
	public int write(CommentVO cvo);
	//public List<CommentVO> getList(Integer pno, Paging pg);
	public CommentDTO getList(Integer pno, Paging pg);
	public int modify(CommentVO cvo);
	public int remove(Integer cno);
	
}
