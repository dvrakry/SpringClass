package com.king.domain;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MemberVO implements UserDetails {
	private String email;
	private String pwd;
	private String nickname;
	private String regdate;
	
	private String auth;
	private boolean enabled;
	private int failcnt;
	
	public MemberVO() {}
	
	public MemberVO(String email, String pwd) {
		this.email = email;
		this.pwd = pwd;
	}
	public MemberVO(String email, String pwd, String nickname) {
		this(email,pwd);
		this.nickname = nickname;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { //인증값비교 로그인해도되는지 아닌지
		ArrayList<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		authList.add(new SimpleGrantedAuthority(auth)); //auth는 지금 내가 로그인한 사람의 auth값을 가져옴
		return authList;
	}

	@Override
	public String getPassword() {
		return getPwd();
	}

	@Override
	public String getUsername() {
		return getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() { //젤밑에 겟언에이블드랑 동일해서 밑에 겟인에이블드가 자동으로 생성이 안됨
		return this.enabled; //vo객체가 가지고 있는 
	}

	
	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public int getFailcnt() {
		return failcnt;
	}

	public void setFailcnt(int failcnt) {
		this.failcnt = failcnt;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	
	
}
