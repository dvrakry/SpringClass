package com.king.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.king.domain.MemberVO;
import com.king.persistence.MemberDAO;

@Service
public class MemberServiceImp implements MemberService{

	private static Logger logger = LoggerFactory.getLogger(MemberServiceImp.class);
	@Inject
	MemberDAO mdao;
	
	@Override
	public int join(MemberVO mvo) {
		return mdao.insert(mvo);
	}

	@Override
	public MemberVO login(MemberVO mvo) {
		return mdao.selectOne(mvo);
	}

	@Override
	public int check(String email) {
		return mdao.selectOne(email);
	}

	@Override
	public List<MemberVO> getList() {
		return mdao.selectList();
	}
	
	@Override
	public MemberVO getMember(String email) {
		return mdao.selectInfo(email);
	}

	@Override
	public int modify(MemberVO mvo) {
		return mdao.update(mvo);
	}

	@Override
	public int resign(String email) {
		return mdao.delete(email);
	}


}
