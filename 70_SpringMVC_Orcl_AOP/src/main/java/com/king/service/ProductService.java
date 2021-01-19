package com.king.service;

import java.util.List;

import com.king.domain.Paging;
import com.king.domain.ProductVO;

public interface ProductService {
	public int register(ProductVO pvo);
	public List<ProductVO> getList(Paging pg);
	public ProductVO getInfo(Integer pno);
	public int modify(ProductVO pvo);
	public int remove(Integer pno);
	public int getCurrPno();
	public int getTotal(Paging pg);
}
