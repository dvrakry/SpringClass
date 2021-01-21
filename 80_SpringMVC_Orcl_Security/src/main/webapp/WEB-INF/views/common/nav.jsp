<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<style>
	ul.nav{
		margin-bottom: 50px;
	}
</style>
</head>
<body>
	<div class="container">
		<h2 class="text-center mt-5">
			<a href="/" class="text-dark text-decoration-non"> Spring
				FrameWork MVC Project </a>
		</h2>
<sec:authorize access="isAuthenticated()"> <!-- 세션 로그인 성공한 사람만 isAuthenticated() 여기서 true false 값을 리턴함 /인증 이프문느낌-->
		<ul class="nav justify-content-center">
		
			<li class="nav-item">
				<a class="nav-link" href="/product/list">상품리스트</a>
			</li>		
				
			<li class="nav-item">
				<sec:authentication property="principal.auth" var="auth"/> <!-- 벨류값을 불러오는 느낌/ principal.auth 여기서 principal은 현재 접속자 유저의 VO객체 -->
				<c:if test="${auth eq 'ADM' }">
						<a class="nav-link" href="/member/list"><sec:authentication property="principal.nickname"/>
						 (<sec:authentication property="principal.email"/>)</a>
				</c:if>
				<c:if test="${auth eq 'MEM' }">
						<a class="nav-link" href='/member/modify?em=<sec:authentication property="principal.email"/>'>
						<sec:authentication property="principal.nickname"/>
						 (<sec:authentication property="principal.email"/>)</a>
				</c:if>
			</li>
			<li class="nav-item">
				<a class="nav-link" href='<c:url value="/member/logout"/>' id="logout">로그아웃</a>
			</li>
		</ul>
		<form action='<c:url value="/member/logout"/>' id="logoutForm" method="post">
			<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"> <!-- 포스트로 보내서 시큐리티가 작동하게함 -->
		</form>
		<script>
			$(function() {
				$("#logout").on("click", function(e) {
					e.preventDefault();//a링크니깐 이동안하게 막기
					$("#logoutForm").submit();
				});
			});
		</script>
</sec:authorize>
<sec:authorize access="isAnonymous()"> <!-- 세션없는 아무나 -->
		<ul class="nav justify-content-center">
			
			<li class="nav-item">
				<a class="nav-link" href="/product/list">상품리스트</a>
			</li>
			
			<li class="nav-item">
				<a class="nav-link" href="/member/join">회원가입</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="/member/login">로그인</a>
			</li>
		</ul>
</sec:authorize>	
