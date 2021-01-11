<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../common/header.jsp"></jsp:include>

<jsp:include page="../common/nav.jsp"></jsp:include>

<div class="col-sm-12">
	<h2>${pvo.title }</h2>
	<h5>${pvo.regdate }=> ${pvo.moddate }</h5>
	<div class="fakeimg">Fake Image</div>
	<p># ${pvo.pno} > ${pvo.price } / ${pvo.readcount } / ${pvo.writer }</p>
	<p>${pvo.content }</p>
</div>
<c:if test="${(ses.email eq pvo.writer) || ses.email eq 'adm@admin.com'}">
<div class="btn-group">
	<a href="/product/modify?pno=${pvo.pno }" class="btn btn-sm btn-outline-warning">수정</a> <!-- 리스트 빼곤 전부 post로 보내야함(폼태그) -->
	<button type="button" id="rmBtn" class="btn btn-sm btn-outline-danger">삭제</button>
	
	<form action="/product/remove" method="post" id="rmForm">
		<input type="hidden" name="pno" value="${pvo.pno }">
	</form>
</div>
</c:if>
<script>
	$(function() {
		
		$(document).on("click", "#rmBtn", function () {
			$("#rmForm").submit(); // rmBtn버튼 누르면 아이디 rmForm 찾아서 히든으로 pno 값을 보냄
		});
		
		
	});
</script>
<jsp:include page="../common/footer.jsp"></jsp:include>