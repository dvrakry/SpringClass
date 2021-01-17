package com.king.persistence;

import java.util.List;

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
	public List<CommentVO> selectList(Integer pno) {
		return sql.selectList(NS+"list" , pno);
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

}
