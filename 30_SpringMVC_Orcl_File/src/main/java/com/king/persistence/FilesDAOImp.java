package com.king.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.king.domain.FilesVO;

@Repository
public class FilesDAOImp implements FilesDAO {

	private static Logger logger = LoggerFactory.getLogger(FilesDAOImp.class);
	private static final String NS = "FilesMapper.";
	
	@Inject
	private SqlSession sql;

	@Override
	public int insert(FilesVO fvo) {
		return sql.insert(NS+"add", fvo);
	}

	@Override
	public List<FilesVO> selectList(Integer pno) {
		return sql.selectList(NS+"list", pno);
	}

	@Override
	public int delete(Integer pno) {
		return sql.delete(NS+"rm",pno);
	}

	@Override
	public List<FilesVO> selectList() {
		return sql.selectList(NS+"all");
	}
}
