package com.king.security;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import com.king.service.MemberService;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	private static Logger logger = LoggerFactory.getLogger(LoginSuccessHandler.class);
	private String authEmail;
	private String authUrl;
	
	private RequestCache reqCache = new HttpSessionRequestCache(); // 이 캐쉬값을 이용해서 리다이렉트로 원래 페이지로 보내주려는거임
	private RedirectStrategy reStg = new DefaultRedirectStrategy();
	
	
	@Inject
	private MemberService msv;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		String email = request.getParameter(authEmail);
		msv.resetFC(email);
		
		HttpSession ses = request.getSession(false);//기존의 세션값을 사용하겠다는 뜻
		if(ses == null) {
			return;
		}else {
			ses.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION); //세션 로그인시 발생한 에러 삭제
			
		}
		SavedRequest savedReq = reqCache.getRequest(request, response);
		if (savedReq != null) {
			//어느 서비스를 이용하기 위해서 리퀘스트 요청이 있었어! ex)/product/register
			String destPage = savedReq.getRedirectUrl();//기존 url값을 저장해서 뽑아내서 스트링이 된것
			reStg.sendRedirect(request, response, destPage);
		} else {
			//여긴 직접 로그인
			reStg.sendRedirect(request, response, authUrl);
		}
		
	}

	public String getAuthEmail() {
		return authEmail;
	}

	public void setAuthEmail(String authEmail) {
		this.authEmail = authEmail;
	}

	public String getAuthUrl() {
		return authUrl;
	}

	public void setAuthUrl(String authUrl) {
		this.authUrl = authUrl;
	}
	
}
