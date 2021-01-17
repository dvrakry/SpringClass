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
<c:if test="${fvo.filelist.size() > 0 }">
<c:forEach items="${fvo.filelist }" var="fvo">
	<li class="list-group-item d-flex justify-content-between align-items-center">
		<c:choose>
			<c:when test="${fvo.ftype >0 }"> <!-- 그럼 이미지 -->
				<img src="/uploads/${fvo.savedir }/${fvo.uuid}_th_${fvo.fname}">
			</c:when>
			<c:otherwise>
				<i class="fa fa-archive" style="font-size: 5em"></i>
			</c:otherwise>
		</c:choose>
		<a href="/uploads/${fvo.savedir }/${fvo.uuid}_${fvo.fname}"> <!-- 다운받기 -->
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
<!-- --------------------------comment------------------------------- -->

<h2>댓글</h2>
<c:if test="${ses.email ne null }">
<form>
<div class="input-group mb-3">
	<div class="input-group-prepend">
		<span class="input-group-text">${ses.email }</span>
	</div>
	<input type="text" id="cmtInput" class="form-control"
		placeholder="Something clever..">
	<div class="input-group-append">
		<button class="btn btn-primary" id="cmtSubmit" type="button">OK</button> <!-- 이동하면 안되서 타입=서브밋X -->
		<button class="btn btn-danger" type="reset">Cancel</button>
	</div>
</div>
</form>
</c:if>
<!-- -----------리스트------------- -->



<script>
function print_list(result) {
	
}

function list_comment(pno) {
	$.getJSON("/comment/pno/"+pno+".json", function(result) { /* 데이터를 받는 제이슨 , result에 리스트 객체가 날라옴*/
			print_list(result);
			
		}
	).fail(function(jqxhr, textStatus, error) {
		   alert("댓글리스트 로딩 오류");
	       var err = textStatus + ", " + error;
	       console.log( "Request Failed: " + err );
	   });
}


function write_comment() {
	let content = $("#cmtInput").val();
	if(content == null || content ==''){
		alert("댓글 내용을 입력해주세요!");
		return false;
	}else{
		let cmtData = {pno: pno, writer: ses_email, content : content};
		$.ajax({
			url: "/comment/write",
			type: "post",
			data: JSON.stringify(cmtData),
			contentType: "application/json; charset=utf-8" //이렇게 보내야 일반String이 아닌 제이슨으로 날라감
			
		}).done(function(result) {
			alert("댓글 등록 완료!");
			list_comment(pno);
		}).fail(function() {
			alert("댓글 등록 오류!");
		}).always(function() {
			$("#cmtInput").val("");
		});
	}
}
</script>

<script>
var pno;
var writer;
var ses_email;

$(function() {
	pno = '<c:out value="${pvo.pno}"/>';
	writer = '<c:out value="${pvo.writer}"/>';
	ses_email = '<c:out value="${ses.email}"/>';
	
	list_comment(pno);
	
	$(document).on("click", "#cmtSubmit", function(e) {
		e.preventDefault();
		write_comment(); //펑션(다른jsp파일에 함수만 따로 저장한걸 불러오는거임)
	});
	
	

	$(function() {
		$(document).on("click", "#rmBtn", function() {
			$("#rmForm").submit(); // rmBtn버튼 누르면 아이디 rmForm 찾아서 히든으로 pno 값을 보냄
		});
	});
});
</script>
<jsp:include page="../common/footer.jsp"></jsp:include>




