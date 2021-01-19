<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<jsp:include page="../common/header.jsp"></jsp:include>

<jsp:include page="../common/nav.jsp"></jsp:include>
<c:choose>
<c:when test="${ses.email eq mvo.email || ses.email eq 'adm@admin.com' }">
<form method="post">
<input type="hidden" name="sese" value="${ses.email }">
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
  <button type="button" class="btn btn-danger" id="rsBtn">회원탈퇴</button>
  <a href="/member/list?pageIdx=${pg.pageIdx }&qty=${pg.qty}&range=${pg.range}&keyword=${pg.keyword}" class="btn btn-sm btn-info">목록</a>
  
 
</form>

<c:choose>
	<c:when test="${ses.email eq 'adm@admin.com' }">
		<form action="/member/resign" method="post" id="rsForm">
			<input type="hidden" name="sign" value="a">
			<input type="hidden" name="email" value="${mvo.email }">
		</form>
	</c:when>
	<c:otherwise>
		<form action="/member/resign" method="post" id="rsForm">
			<input type="hidden" name="sign" value="m">
			<input type="hidden" name="email" value="${mvo.email }">
		</form>
	</c:otherwise>
</c:choose>
<script>
	$(function() {
		$(document).on("click", "#rsBtn", function(e) {
			e.preventDefault();
			$("#rsForm").submit();
		});
	});
</script>
</c:when>
<c:otherwise>
	<h1>권한이 필요한 페이지 입니다.</h1>
</c:otherwise>
</c:choose>



<jsp:include page="../common/footer.jsp"></jsp:include>