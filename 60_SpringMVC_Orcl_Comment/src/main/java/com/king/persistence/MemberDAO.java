package com.king.persistence;

import java.util.List;

import com.king.domain.MemberVO;
import com.king.domain.Paging;

public interface MemberDAO {
	public int insert(MemberVO mvo);
	public MemberVO selectOne(MemberVO mvo);
	public int selectOne(String email);
	public MemberVO selectInfo(String email);
	public List<MemberVO> selectList(Paging pg);
	public int update(MemberVO mvo);
	public int delete(String email);
	public int selectTotal(Paging pg);
}
