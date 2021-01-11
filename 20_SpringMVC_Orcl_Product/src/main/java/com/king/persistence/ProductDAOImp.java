package com.king.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.king.domain.ProductVO;

@Repository
public class ProductDAOImp implements ProductDAO {

	private static Logger logger = LoggerFactory.getLogger(ProductDAOImp.class);
	private static final String NS = "ProductMapper.";
	
	@Inject
	private SqlSession sql;
	
	@Override
	public int insert(ProductVO pvo) {
		return sql.insert(NS+"reg", pvo);
	}

	@Override
	public List<ProductVO> selectList() {
		return sql.selectList(NS+"list");
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
}
