<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../common/header.jsp"></jsp:include>

<jsp:include page="../common/nav.jsp"></jsp:include>
<c:choose>
<c:when test="${ses.email ne '' && ses.email ne null}">
<div class="row">
	<div class="col-md-12 order-md-1">
		<h4 class="mb-3">상품 등록</h4>
		<form method="post">
				<div class="mb-3">
				<label for="address">상품명</label> <input type="text"
					class="form-control" id="address" name="title" placeholder=""
					required>
			</div>

			<div class="mb-3">
				<label for="username">등록자</label>
				<div class="input-group">
					<div class="input-group-prepend">
						<span class="input-group-text">@</span>
					</div>
					<input type="text" class="form-control" id="username" name="writer" value="${ses.email }"
						placeholder="" readonly>
				</div>
			</div>

			<div class="mb-3">
				<label for="email">상품가격<span class="text-muted">(Optional)</span></label>
				<input type="text" class="form-control" id="email" name="price"
					placeholder="" required>
			</div>

			<div class="mb-3">
				<label for="address">상품설명</label> 
				<textarea rows="5" name="content" class="form-control" required></textarea>
			</div>
			<hr class="mb-4">
			<button class="btn btn-primary btn-lg btn-block" type="submit" >
			<i class="fa fa-check-square-o"></i>등록</button>
		</form>
	</div>
</div>
</c:when>
<c:otherwise>
	<h1>권한이 필요한 페이지입니다!</h1>
</c:otherwise>
</c:choose>
<jsp:include page="../common/footer.jsp"></jsp:include>