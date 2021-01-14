<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../common/header.jsp"></jsp:include>
<style>
#files{display: none;}
</style>
<jsp:include page="../common/nav.jsp"></jsp:include>
<c:choose>
	<c:when
		test="${pvo.writer eq ses.email || ses.email eq 'amd@admin.com' }">
		<div class="row">
			<div class="col-md-12 order-md-1">
				<h4 class="mb-3">상품 수정</h4>
				<form method="post" enctype="multipart/form-data">
					<input type="hidden" name="pno" value="${pvo.pno }">
					<input type="hidden" name="pageIdx" value="${pg.pageIdx }">
					<input type="hidden" name="qty" value="${pg.qty }">
					

					<div class="mb-3">
						<label for="address">상품명</label> <input type="text"
							value="${pvo.title }" class="form-control" id="address"
							name="title" placeholder="" required>
					</div>

					<div class="mb-3">
						<label for="username">등록자</label>
						<div class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text">@</span>
							</div>
							<input type="text" class="form-control" id="username"
								name="writer" value="${pvo.writer }" placeholder="" readonly>
						</div>
					</div>

					<div class="mb-3">
						<label for="email">상품가격<span class="text-muted">(Optional)</span></label>
						<input type="text" class="form-control" id="email" name="price"
							value="${pvo.price }" placeholder="" required>
					</div>

					<div class="mb-3">
						<label for="address">상품설명</label>
						<textarea rows="5" name="content" class="form-control" required>${pvo.content }</textarea>
					</div>

					<div class="mb-3">
						<input type="file" class="form-control" id="files" name="files"
							multiple>
						<!-- 숨겨둠 css로 -->
						<button type="button" class="btn btn-outline-info btn-block"
							id="trigger">파일첨부</button>
					</div>

					<ul class="list-group" id="fileZone">
						<c:if test="${pvo.filelist.size() > 0 }">
							<c:forEach items="${pvo.filelist }" var="fvo">
								<li
									class="list-group-item d-flex justify-content-between align-items-center">
									<c:choose>
										<c:when test="${fvo.ftype >0 }">
											<!-- 그럼 이미지 -->
											<img
												src="/uploads/${fvo.savedir }/${fvo.uuid}_th_${fvo.fname}">
										</c:when>
										<c:otherwise>
											<i class="fa fa-archive" style="font-size: 5em"></i>
										</c:otherwise>
									</c:choose> <a href="/uploads/${fvo.savedir }/${fvo.uuid}_${fvo.fname}">
										<span class="badge badge-light">${fvo.fname }</span>
								</a>
								</li>
							</c:forEach>
						</c:if>
					</ul>

					<hr class="mb-4">
					<button class="btn btn-primary btn-lg btn-block" type="submit">
						<i class="fa fa-check-square-o"></i>수정
					</button>
				</form>
			</div>
		</div>
		<script src="/resources/js/files.js"></script>
		<!-- 선언부를 따로 빼놓음 -->
		<script>

			$(function() {
				$(document).on("click", "#trigger", function() {
					$("#files").click();	
				});
				
				$(document).on("change", "#files", function() {
					$("button[type=submit]").attr("disabled", false);
					let formObjs = $("#files");
					console.log(formObjs);
					let fileObjs = formObjs[0].files;
					let ul = $("#fileZone");
					ul.html("");
					
					for (let fileObj of fileObjs) {//순서가 있으면 of로 꺼내도됨
						let li = ' <li class="list-group-item d-flex justify-content-between align-items-left">';
						if(fileExtAndSizeValidation(fileObj.name, fileObj.size)){
							li += '<i class="fa fa-check" style="color:green;"></i>';
							li += fileObj.name+ '<span class="badge badge-success">';
						}else{
							li += '<i class="fa fa-exclamation-circle" style="color:red;"></i>';
							li += fileObj.name+ '<span class="badge badge-danger">';
							$("button[type=submit]").attr("disabled", true);
						}
						li += printSize(fileObj.size)+'</span>';
						ul.append(li);
					}
					
				});
				
			});

	</script>
	</c:when>
	<c:otherwise>
		<h1>권한이 필요한 페이지입니다!</h1>
	</c:otherwise>
</c:choose>


<jsp:include page="../common/footer.jsp"></jsp:include>