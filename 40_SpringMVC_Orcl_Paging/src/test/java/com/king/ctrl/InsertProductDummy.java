package com.king.ctrl;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.king.domain.ProductVO;
import com.king.persistence.ProductDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class InsertProductDummy {
	private static Logger logger = LoggerFactory.getLogger(InsertProductDummy.class);
	
	@Inject
	private ProductDAO pdao;
	
	@Test
	public void makeDummyOfProduct() throws Exception{
		for (int i = 0; i < 100; i++) {
			ProductVO pvo = new ProductVO();
			pvo.setTitle("상품명 테스트 " + i);
			pvo.setContent("상품 내용 테스트 " + i);
			int r = (int)(Math.random()*256);
			pvo.setWriter("tester"+r+"@tester.com");
			int p = (int)(Math.random()*1000);
			double d = Double.parseDouble(String.format("%.2f", Math.random()));
			pvo.setPrice(p+d);
			pdao.insert(pvo);
		}
	}
	
}
