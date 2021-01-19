package com.king.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.king.domain.MemberVO;
import com.king.domain.Paging;

@Repository
public class MemberDAOImp implements MemberDAO {
	private static Logger logger = LoggerFactory.getLogger(MemberDAOImp.class);
	private static final String NS = "MemberMapper.";
	
	@Inject
	SqlSession sql;

	@Override
	public int insert(MemberVO mvo) {
		return sql.insert(NS+"join", mvo);
	}

	@Override
	public MemberVO selectOne(MemberVO mvo) {
		return sql.selectOne(NS+"login", mvo);
	}

	@Override
	public int selectOne(String email) {
		return sql.selectOne(NS+"check", email);
	}

	@Override
	public MemberVO selectInfo(String email) {
		return sql.selectOne(NS+"info", email) ;
	}

	@Override
	public int update(MemberVO mvo) {
		return sql.update(NS+"mod", mvo);
	}

	@Override
	public int delete(String email) {
		return sql.delete(NS+"resign", email);
	}

	@Override
	public List<MemberVO> selectList(Paging pg) {
		
		return sql.selectList(NS+"list", pg);
	}

	@Override
	public int selectTotal(Paging pg) {
		return sql.selectOne(NS+"tt" , pg);
	}
}
