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
<ul class="list-group" id="fileZone">
<c:if test="${pvo.filelist.size() > 0 }">
<c:forEach items="${pvo.filelist }" var="fvo">
	<li class="list-group-item d-flex justify-content-between align-items-center">
		<c:choose>
			<c:when test="${fvo.ftype >0 }"> <!-- 그럼 이미지 -->
				<img src="/uploads/${fvo.savedir }/${fvo.uuid}_th_${fvo.fname}">
			</c:when>
			<c:otherwise>
				<i class="fa fa-archive" style="font-size: 5em"></i>
			</c:otherwise>
		</c:choose>
		<a href="/uploads/${fvo.savedir }/${fvo.uuid}_${fvo.fname}">
		<span class="badge badge-light">${fvo.fname }</span></a>
	</li>
</c:forEach>
</c:if>	
</ul>
<div class="btn-group">
<a href="/product/list?pageIdx=${pg.pageIdx }&qty=${pg.qty}&range=${pg.range}&keyword=${pg.keyword}" class="btn btn-sm btn-info">목록</a>
<c:if test="${(ses.email eq pvo.writer) || ses.email eq 'adm@admin.com'}">
	
	<a href="/product/modify?pno=${pvo.pno }&pageIdx=${pg.pageIdx }&qty=${pg.qty}&range=${pg.range}&keyword=${pg.keyword}" 
	class="btn btn-sm btn-outline-warning">수정</a> <!-- 리스트 빼곤 전부 post로 보내야함(폼태그) -->
	<button type="button" id="rmBtn" class="btn btn-sm btn-outline-danger">삭제</button>
	
	<form action="/product/remove" method="post" id="rmForm">
		<input type="hidden" name="pno" value="${pvo.pno }">
		<input type="hidden" name="pageIdx" value="${pg.pageIdx }">
		<input type="hidden" name="qty" value="${pg.qty }">
		<input type="hidden" name="range" value="${pg.range }">
		<input type="hidden" name="keyword" value="${pg.keyword }">
	</form>
</c:if>
</div>
<script>
	$(function() {
		
		$(document).on("click", "#rmBtn", function () {
			$("#rmForm").submit(); // rmBtn버튼 누르면 아이디 rmForm 찾아서 히든으로 pno 값을 보냄
		});
		
	});
</script>
<jsp:include page="../common/footer.jsp"></jsp:include>