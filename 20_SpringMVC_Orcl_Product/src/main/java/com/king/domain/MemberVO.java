package com.king.domain;


public class MemberVO {
	private String email;
	private String pwd;
	private String nickname;
	private String regdate;
	
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

	
}
