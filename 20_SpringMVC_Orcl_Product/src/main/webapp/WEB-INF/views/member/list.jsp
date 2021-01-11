<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<jsp:include page="../common/header.jsp"></jsp:include>

<jsp:include page="../common/nav.jsp"></jsp:include>
<c:choose>
<c:when test="${ses.email eq 'amd@admin.com' }">
  <h2>Member List Table</h2>
  <p>spring project member list</p>            
  <table class="table">
    <thead>
      <tr>
        <th>계정</th>
        <th>별칭</th>
        <th>비밀번호</th>
        <th>가입일</th>
        <th>관리</th>
      </tr>
    </thead>
    <tbody>
       <c:forEach items="${list }" var="mvo">
      <tr>
        <td>${mvo.email }</td>
        <td>${mvo.nickname }</td>
        <td>${mvo.pwd }</td>
        <td>${mvo.regdate }</td>
        <td>
        	<a id="mod" class="btn btn-outline-warning btn-sm" href="/member/modify?em=${mvo.email }">수정</a>
        	<button type="button" class="btn btn-outline-danger btn-sm resign" >탈퇴</button>
        </td>
      </tr>
      </c:forEach>
    </tbody>
  </table>
  <!-- 포스트로 보내는방식 -->
  <form id="resignForm" method="post" action="/member/resign">
  	<input type="hidden" name="email">
  	<input type="hidden" name="sign" value="a">
  </form>
<script>
$(function(){
	$(".resign").on("click", function() {
		let emailVal = $(this).closest("tr").find("td:first-child").text();
		console.log(emailVal);
		$("#resignForm > input").val(emailVal);
		$("#resignForm").submit();
	});
	
});
</script>
</c:when>
<c:otherwise>
	<h1>권한이 필요한 페이지 입니다!</h1>
</c:otherwise>
</c:choose>

<jsp:include page="../common/footer.jsp"></jsp:include>
