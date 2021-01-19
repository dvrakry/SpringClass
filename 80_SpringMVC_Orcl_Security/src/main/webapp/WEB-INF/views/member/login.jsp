<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<jsp:include page="../common/header.jsp"></jsp:include>

<jsp:include page="../common/nav.jsp"></jsp:include>

<form method="post"> <!-- 포스트, 즉 데이터베이스에 접근하는건 다 cfrs 확인해야됨(위변조방지) -->
<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
  <div class="form-group">
    <label for="email">Email address:</label>
    <input type="email" value="" class="form-control" placeholder="Enter email" id="email" name="email">
  </div>
  <div class="form-group">
    <label for="pwd">Password:</label>
    <input type="password" value="" class="form-control" placeholder="Enter password" id="pwd" name="pwd">
  </div>
  <div class="form-group form-check">
    <label class="form-check-label">
      <input class="form-check-input" type="checkbox"> Remember me
    </label>
  </div>
  <button type="submit" class="btn btn-primary">Login</button>
</form>


<jsp:include page="../common/footer.jsp"></jsp:include>