package com.king.persistence;

import java.util.List;

import com.king.domain.FilesVO;

public interface FilesDAO {
	public int insert(FilesVO fvo);
	public List<FilesVO> selectList(Integer pno);
	public int delete(Integer pno);
}
