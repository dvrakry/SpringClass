<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<jsp:include page="../common/header.jsp"></jsp:include>

<jsp:include page="../common/nav.jsp"></jsp:include>
<c:choose>
<c:when test="${ses.email eq 'adm@admin.com' }">
	<div class="input-group mb-3">	
	<form action="/member/list" class="form-inline">
	<div class="input-group-prepend">
		<select class="form-control" name="range">
			<option value="e">email</option>
			<option value="n">nickname</option>
		</select>
	</div>
	<input type="text" class="form-control" placeholder="검색어" name="keyword">
	<div class="input-group-append">
		<button class="btn btn-success" type="submit">Go</button>
	</div>
	</form>
</div>
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
        	<a id="mod" class="btn btn-outline-warning btn-sm" href="/member/modify?em=${mvo.email }&pageIdx=${pgbld.pg.pageIdx }&qty=${pgbld.pg.qty}&range=${pgbld.pg.range}&keyword=${pgbld.pg.keyword}">수정</a>
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
  	<input type="hidden" name="pageIdx" value="${pg.pageIdx }">
	<input type="hidden" name="qty" value="${pg.qty }">
	<input type="hidden" name="range" value="${pg.range }">
	<input type="hidden" name="keyword" value="${pg.keyword }">
  </form>
  
  <ul class="pagination">
	<c:if test="${pgbld.prev }">
		<li class="page-item"><a class="page-link"
		 href="/member/list?pageIdx=${pgbld.firstPageIdx-1 }&qty=${pgbld.pg.qty }&range=${pgbld.pg.range}&keyword=${pgbld.pg.keyword}"> 
		<i class="fa fa-angle-double-left"></i></a></li>
	</c:if>
	<c:forEach begin="${pgbld.firstPageIdx }" end="${pgbld.lastPageIdx }" var="i">
		<li class="page-item ${pgbld.pg.pageIdx == i ? 'active' : '' }"><a class="page-link" 
		href="/member/list?pageIdx=${i }&qty=${pgbld.pg.qty}&range=${pgbld.pg.range}&keyword=${pgbld.pg.keyword}">${i }</a></li>
	</c:forEach>
	<c:if test="${pgbld.next }">
		<li class="page-item"><a class="page-link" 
		href="/member/list?pageIdx=${pgbld.lastPageIdx+1 }&qty=${pgbld.pg.qty }&range=${pgbld.pg.range}&keyword=${pgbld.pg.keyword}"> 
		<i class="fa fa-angle-double-right"></i></a></li>
	</c:if>
</ul>
  
  
<script>
$(function(){
	$(".resign").on("click", function() {
		let emailVal = $(this).closest("tr").find("td:first-child").text();
		console.log(emailVal);
		$("#resignForm > input:first-child").val(emailVal);
		$("#resignForm").submit();
	});
	
});
</script>
</c:when>
<c:otherwise>
	<h1>수정이 완료되었습니다!</h1>
</c:otherwise>
</c:choose>

<jsp:include page="../common/footer.jsp"></jsp:include>
