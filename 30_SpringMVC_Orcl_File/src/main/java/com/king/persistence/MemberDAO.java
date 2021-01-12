package com.king.persistence;

import java.util.List;

import com.king.domain.MemberVO;

public interface MemberDAO {
	public int insert(MemberVO mvo);
	public MemberVO selectOne(MemberVO mvo);
	public int selectOne(String email);
	public MemberVO selectInfo(String email);
	public List<MemberVO> selectList();
	public int update(MemberVO mvo);
	public int delete(String email);
}
