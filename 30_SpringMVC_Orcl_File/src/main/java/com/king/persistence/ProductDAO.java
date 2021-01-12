package com.king.persistence;

import java.util.List;

import com.king.domain.ProductVO;

public interface ProductDAO {
	public int insert(ProductVO pvo);
	public List<ProductVO> selectList();
	public ProductVO selectOne(Integer pno);
	public int update(ProductVO pvo);
	public int delete(Integer pno);
	public int selectPno();
}
