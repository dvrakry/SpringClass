package com.king.ctrl;

import java.util.ArrayList;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.king.domain.MemberVO;
import com.king.persistence.MemberDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class InsertMemberDummy {
	private static Logger logger = LoggerFactory.getLogger(InsertMemberDummy.class);
	
	@Inject //객체만들어달라~~
	private MemberDAO mdao;//MemberImp부른거와 동일
	
	@Test
/*	public void makeDummyOfMember() throws Exception{
		for (int i = 0; i < 100; i++) {
			MemberVO mvo = new MemberVO();
			mvo.setEmail("tester"+i+"@tester.com");
			mvo.setPwd("1111");
			mvo.setNickname("tester"+i);
			mdao.insert(mvo);
		}
	}*/

	public void getInfoOfMember() throws Exception{
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		for (int i = 0; i < 10; i++) {
			MemberVO mvo = mdao.selectInfo("tester"+i+"@tester.com");
			list.add(mvo);
	      }
		for (MemberVO mvo : list) {
			logger.info(mvo.getEmail() + "|" + mvo.getNickname() + "|" + mvo.getPwd() + "|" + mvo.getRegdate());
		}
	}
}
