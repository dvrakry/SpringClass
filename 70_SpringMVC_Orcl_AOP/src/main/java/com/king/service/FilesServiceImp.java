package com.king.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.king.domain.FilesVO;
import com.king.persistence.FilesDAO;

@Service
public class FilesServiceImp implements FilesService {

	private static Logger logger = LoggerFactory.getLogger(FilesServiceImp.class);
	
	@Inject
	private FilesDAO fdao;
	
	@Override
	public int attach(FilesVO fvo) {
		return fdao.insert(fvo);
	}

	@Override
	public List<FilesVO> getList(Integer pno) {
		return fdao.selectList(pno);
	}

	@Override
	public int remove(Integer pno) {
		return fdao.delete(pno);
	}
}
