<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../common/header.jsp"></jsp:include>

<jsp:include page="../common/nav.jsp"></jsp:include>

<div class="align-items-right mb-3">
	<c:if test="${ses.email ne null && ses.email ne ''}">
			<a href="/product/register" class="btn btn-sm btn-outline-primary">상품등록</a>
	</c:if>
</div>

<div class="row">
<c:forEach items="${list }" var="pvo">
	<div class="col-md-4">
		<div class="card mb-4 shadow-sm">
			<svg class="bd-placeholder-img card-img-top" width="100%"
				height="225" xmlns="http://www.w3.org/2000/svg"
				preserveAspectRatio="xMidYMid slice" focusable="false" role="img"
				aria-label="Placeholder: Thumbnail">
				<title>Placeholder</title><rect width="100%" height="100%"
					fill="#55595c" />
				<text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>
			<div class="card-body">
				<p class="card-text">
				<a href="/product/info?pno=${pvo.pno }&pageIdx=${pgbld.pg.pageIdx}&qty=${pgbld.pg.qty}">
				${pvo.title }</a></p>
				<div class="d-flex justify-content-between align-items-center">
					<div class="btn-group">
						<button type="button" class="btn btn-sm btn-outline-secondary">${pvo.writer }</button>
						<button type="button" class="btn btn-sm btn-outline-secondary">${pvo.readcount}</button>
					</div>
					<small class="text-muted">${pvo.price }</small>&nbsp;
					<small class="text-muted">${pvo.moddate }</small>
				</div>
			</div>
		</div>
	</div>
</c:forEach>
</div>
<!-- 페이지네이션 자리 -->
<div class="row"> 
<jsp:include page="../common/paging.jsp"></jsp:include> <!-- 이건 모두 같은 페이지네이션 쓸때 유용 -->
</div>



<jsp:include page="../common/footer.jsp"></jsp:include>