package com.king.persistence;

import java.util.List;

import com.king.domain.CommentVO;
import com.king.domain.Paging;

public interface CommentDAO {
	public int insert(CommentVO cvo);
	public List<CommentVO> selectList(Integer pno, Paging pg);
	public int selectOne(Integer pno);
	public int update(CommentVO cvo);
	public int delete(Integer cno);
	
}
