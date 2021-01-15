package com.king.service;

import java.util.List;

import com.king.domain.CommentVO;
import com.king.domain.Paging;

public interface CommentService {
	public int write(CommentVO cvo);
	public List<CommentVO> getList(Integer pno);
	public int modify(CommentVO cvo);
	public int remove(Integer cno);
	
}
