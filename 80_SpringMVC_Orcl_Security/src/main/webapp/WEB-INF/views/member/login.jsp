<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<jsp:include page="../common/header.jsp"></jsp:include>

<jsp:include page="../common/nav.jsp"></jsp:include>

<form method="post"> <!-- 포스트, 즉 데이터베이스에 접근하는건 다 cfrs 확인해야됨(위변조방지) -->
<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
  <div class="form-group">
    <label for="email">Email address:</label>
    <input type="email" value="${email }" class="form-control" placeholder="Enter email" id="email" name="email"> <!-- 여기 name 값은 정말중요! 다 연결되어있음 -->
  </div>
  <div class="form-group">
    <label for="pwd">Password:</label>
    <input type="password" value="${pwd }" class="form-control" placeholder="Enter password" id="pwd" name="pwd">
  </div>
  
  <c:if test="${not empty err_msg}">
  	<div class="form-group">
  		<p class="text-danger">
  			<c:choose>
  				<c:when test="${err_msg eq 'Bad credentials' }">
  					<c:set var="errTxt" value="이메일 혹은 비밀번호가 틀렸습니다!."/>
  				</c:when>
  				<c:when test="${err_msg eq 'User is disabled' }">
  					<c:set var="errTxt" value="계정이 비활성화 되어 있습니다!."/>
  				</c:when>
  				<c:otherwise>
  					<c:set var="errTxt" value="관리자에게 문의하세요!."/>
  				</c:otherwise>
  			</c:choose>
  			${errTxt }
  		</p>
  	</div>
  </c:if>
  
  <div class="form-group form-check">
    <label class="form-check-label">
      <input class="form-check-input" type="checkbox" name="remember-me"> Remember me
    </label>
  </div>
  
  <button type="submit" class="btn btn-primary">Login</button>
</form>


<jsp:include page="../common/footer.jsp"></jsp:include>