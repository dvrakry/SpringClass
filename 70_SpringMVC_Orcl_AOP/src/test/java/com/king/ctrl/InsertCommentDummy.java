package com.king.ctrl;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.king.domain.CommentVO;
import com.king.domain.MemberVO;
import com.king.persistence.MemberDAO;
import com.king.service.CommentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/security-context.xml"})
public class InsertCommentDummy {
	private static Logger logger = LoggerFactory.getLogger(InsertCommentDummy.class);
	
	@Inject //객체만들어달라~~
	private CommentService csv;//MemberImp부른거와 동일
	
	@Inject
	private MemberDAO mdao;
	
	@Inject
	private BCryptPasswordEncoder bcpEncoder;
	
	
	@Test
	public void makeDummyComment() throws Exception {
		for (int i = 1032; i < 1102 ; i++) { // pno: 1~516
			int r = (int)(Math.random()*128);
			
			for (int j = 0; j < r; j++) {
				CommentVO cvo = new CommentVO();
				cvo.setPno(i);
				cvo.setContent(j + "번째 테스트 댓글");
				int rr = (int)(Math.random()*128);
				cvo.setWriter("tester" +rr+ "@tester.com");
				csv.write(cvo);
			}
		}
	}
	
	@Test
	public void makeDummyOfMember() throws Exception{
		String encPwd = bcpEncoder.encode("1111");
		for (int i = 0; i < 128; i++) {
			MemberVO mvo = new MemberVO();
			mvo.setEmail("tester"+i+"@tester.com");
			mvo.setPwd(encPwd);
			mvo.setNickname("TESTER"+i);
			mdao.insert(mvo);
		}
	}
	
	
	
	
}
