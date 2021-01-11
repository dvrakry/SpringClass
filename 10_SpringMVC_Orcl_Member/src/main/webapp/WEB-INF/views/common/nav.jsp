<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

</head>
<body>
	<div class="container">
		<h2 class="text-center mt-5">
			<a href="/" class="text-dark text-decoration-non"> Spring
				FrameWork MVC Project </a>
		</h2>
<c:choose>
	<c:when test="${ses != null }">
		<ul class="nav justify-content-center">
			<li class="nav-item">
				<c:choose>
					<c:when test="${ses.email eq 'admin@admin.com' }">
						<a class="nav-link" href="/member/list">${ses.nickname }(${ses.email })</a>
					</c:when>
					<c:otherwise>
						<a class="nav-link" href="/member/modify?me=${ses.email }">${ses.nickname }(${ses.email })</a>
					</c:otherwise>
				</c:choose>	
			</li>
			<li class="nav-item">
				<a class="nav-link" href="/member/logout">로그아웃</a>
			</li>
		</ul>
	</c:when>
	<c:otherwise>
		<ul class="nav justify-content-center">
			<li class="nav-item">
				<a class="nav-link" href="/member/join">회원가입</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="/member/login">로그인</a>
			</li>
		</ul>
	</c:otherwise>
</c:choose>