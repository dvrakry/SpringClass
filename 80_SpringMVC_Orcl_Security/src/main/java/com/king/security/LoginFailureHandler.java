package com.king.security;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.king.service.MemberService;

public class LoginFailureHandler implements AuthenticationFailureHandler {
	private static Logger logger = LoggerFactory.getLogger(LoginFailureHandler.class);
	private String authEmail; //adm@admin.com(X) <beans:property name="authEmail" value="email"/> 여기 밸류를 authmail로 가져오는거임
	private String authPwd;
	private String errMsg; //<beans:property name="errMsg" value="err_msg"/>
	private String failUrl; //<beans:property name="failUrl" value="/member/login?error"/>
	
	@Inject
	MemberService msv;
	

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		logger.info(">>>>>authEmail: " + authEmail);
		String email = request.getParameter(authEmail); //adm@admin.com(X) <beans:property name="authEmail" value="email"/> 여기 밸류를 authmail로 가져오는거임
		logger.info(">>>>>email: " + email);
		String pwd = request.getParameter(authPwd);
		logger.info(">>>>>authPwd" + authPwd);
		logger.info(">>>>>pwd: " + pwd);
		String ex_msg = exception.getMessage();
		logger.info(">>>>>ex_msg: " + ex_msg);
		if (exception instanceof BadCredentialsException
				|| exception instanceof InternalAuthenticationServiceException) {
			loginFailCountUp(email);
		}
		request.setAttribute(authEmail, email);
		request.setAttribute(authPwd, pwd);
		request.setAttribute(errMsg, ex_msg);
		request.setAttribute("failUrl", failUrl);
		request.getRequestDispatcher(failUrl).forward(request, response);
		logger.info(">>>>>failUrl: " + failUrl);
	}

	private void loginFailCountUp(String email) {
		msv.upFailCount(email);
		int fc = msv.getFC(email);
		if(fc >= 5) {
			msv.lockEmail(email);
		}
	}


	public String getAuthEmail() {
		return authEmail;
	}


	public void setAuthEmail(String authEmail) {
		this.authEmail = authEmail;
	}


	public String getAuthPwd() {
		return authPwd;
	}


	public void setAuthPwd(String authPwd) {
		this.authPwd = authPwd;
	}


	public String getErrMsg() {
		return errMsg;
	}


	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}


	public String getFailUrl() {
		return failUrl;
	}


	public void setFailUrl(String failUrl) {
		this.failUrl = failUrl;
	}
	
}
