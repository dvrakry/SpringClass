package com.king.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.king.domain.ProductVO;
import com.king.persistence.ProductDAO;

@Service
public class ProductServiceImp implements ProductService {

	private static Logger logger = LoggerFactory.getLogger(ProductServiceImp.class);
	
	@Inject
	private ProductDAO pdao; //imp 인젝트하면 안되고 인터페이스 인젝트해야됨 다형성 떄문에 
	
	@Override
	public int register(ProductVO pvo) {
		return pdao.insert(pvo);
	}

	@Override
	public List<ProductVO> getList() {
		return pdao.selectList();
	}

	@Override
	public ProductVO getInfo(Integer pno) {
		return pdao.selectOne(pno);
	}

	@Override
	public int modify(ProductVO pvo) {
		return pdao.update(pvo);
	}

	@Override
	public int remove(Integer pno) {
		return pdao.delete(pno);
	}
}
