package com.king.service;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.king.domain.MemberVO;
import com.king.persistence.MemberDAO;

public class AuthMemberDetailsService implements UserDetailsService {
	private static Logger logger = LoggerFactory.getLogger(AuthMemberDetailsService.class);

	@Inject
	private MemberDAO mdao;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException { //이걸부르면 db에서 로그인한 아이디를 가져와야됨
		MemberVO mvo = mdao.selectInfo(email);
		if(mvo == null) {
			throw new UsernameNotFoundException(email);
		}
		return mvo;
	}
	
}
