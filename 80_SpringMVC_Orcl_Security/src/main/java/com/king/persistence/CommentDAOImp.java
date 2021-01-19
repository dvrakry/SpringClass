package com.king.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.king.domain.CommentVO;
import com.king.domain.Paging;

@Repository
public class CommentDAOImp implements CommentDAO{
	private static Logger logger = LoggerFactory.getLogger(CommentDAOImp.class);
	private static final String NS = "CommentMapper.";
	
	@Inject
	SqlSession sql;
	
	@Override
	public int insert(CommentVO cvo) {
		return sql.insert(NS+"add", cvo);
	}

	@Override
	public List<CommentVO> selectList(Integer pno, Paging pg) {
		Map<String, Object> map = new HashMap<>();
		map.put("pno", pno);
		map.put("pg", pg);
		return sql.selectList(NS+"list" , map);
	}

	@Override
	public int selectOne(Integer pno) {
		return sql.selectOne(NS+"total", pno);
	}

	@Override
	public int update(CommentVO cvo) {
		return sql.update(NS+"mod", cvo);
	}

	@Override
	public int delete(Integer cno) {
		return sql.delete(NS+"rm", cno);
	}

	@Override
	public int deleteAll(Integer pno) {
		return sql.delete(NS+"rmAll", pno);
	}

	@Override
	public int selectPno(Integer cno) {
		return sql.selectOne(NS+"pno", cno);
	}

}
