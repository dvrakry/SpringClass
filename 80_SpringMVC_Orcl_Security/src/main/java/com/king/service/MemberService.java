package com.king.service;

import java.util.List;

import com.king.domain.MemberVO;
import com.king.domain.Paging;

public interface MemberService {
	public int join(MemberVO mvo);
	public MemberVO login(MemberVO mvo);
	public int check(String email);
	public MemberVO getMember(String email);
	public List<MemberVO> getList(Paging pg);
	public int modify(MemberVO mvo);
	public int resign(String email);
	public int getTotal(Paging pg);
	
}
