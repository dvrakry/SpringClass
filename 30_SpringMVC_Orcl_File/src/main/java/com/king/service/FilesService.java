package com.king.service;

import java.util.List;

import com.king.domain.FilesVO;

public interface FilesService {
	public int attach(FilesVO fvo);
	public List<FilesVO> getList(Integer pno);
	public int remove(Integer pno);
}
