package com.king.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.king.domain.Paging;
import com.king.domain.ProductVO;

@Repository
public class ProductDAOImp implements ProductDAO {

	private static Logger logger = LoggerFactory.getLogger(ProductDAOImp.class);
	private static final String NS = "ProductMapper.";//매퍼에 네임스페이스가 ProductMapper 이걸로 정의되어있음
	
	@Inject
	private SqlSession sql;
	
	@Override
	public int insert(ProductVO pvo) {
		return sql.insert(NS+"reg", pvo);
	}

	@Override
	public List<ProductVO> selectList(Paging pg) {
		return sql.selectList(NS+"list" , pg);
	}

	@Override
	public ProductVO selectOne(Integer pno) {
		return sql.selectOne(NS+"info", pno);
	}

	@Override
	public int update(ProductVO pvo) {
		return sql.update(NS+"mod", pvo);
	}

	@Override
	public int delete(Integer pno) {
		return sql.delete(NS+"rm",pno);
	}

	@Override
	public int selectPno() {
		return sql.selectOne(NS+"pno");
	}

	@Override
	public int selectTotal(Paging pg) {
		return sql.selectOne(NS+"tt", pg);
	}
}
