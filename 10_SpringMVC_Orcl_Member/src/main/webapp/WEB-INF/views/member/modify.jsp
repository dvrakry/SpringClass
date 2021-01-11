<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<jsp:include page="../common/header.jsp"></jsp:include>

<jsp:include page="../common/nav.jsp"></jsp:include>

<form method="post">
  <div class="form-group">
    <label for="email">Email address:</label>
    <input type="email" class="form-control" placeholder="Enter email" id="email" name="email" value="${mvo.email }" readonly>
  </div>
  <div class="form-group">
    <label for="pwd">Password:</label>
    <input type="password" class="form-control" placeholder="Enter password" id="pwd" name="pwd" readonly>
  </div>
   <div class="form-group">
    <label for="nickname">Nickname:</label>
    <input type="text" class="form-control" placeholder="Enter Nickname" id="nick" name="nickname" value="${mvo.nickname }">
  </div>
  <div class="form-group form-check">
  </div>
  <button type="submit" class="btn btn-primary">수정완료</button>
  <a href="/member/resign?email=${mvo.email }" class="btn btn-danger">회원탈퇴</a>
</form>




<jsp:include page="../common/footer.jsp"></jsp:include>