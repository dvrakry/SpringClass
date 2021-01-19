<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../common/header.jsp"></jsp:include>
<style>
 .card span{
 	margin-right: 50px;
 }
</style>
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
<div id="accordion"></div>

<ul class="pagination"></ul> <!-- 댓글 페이지네이션 -->

<!-- -----------리스트------------- -->


<script>//여기가 스크립트 시작! info.jsp 읽으면 여기부터 읽음
var pno;
var writer;
var ses_email;
var total;

$(function() {
	pno = '<c:out value="${pvo.pno}"/>';
	writer = '<c:out value="${pvo.writer}"/>';
	ses_email = '<c:out value="${ses.email}"/>';
	
	list_comment(pno,1); //189번째 줄 return으로 받아옴 total을, 이 함수에서 토탈을 받아옴
	
	
	
	

	$(function() {
		$(document).on("click", "#rmBtn", function() {
			$("#rmForm").submit(); // rmBtn버튼 누르면 아이디 rmForm 찾아서 히든으로 pno 값을 보냄
		});
	});
		
		
		
});
</script>
<script src="/resources/js/comment.js"></script> <!-- 함수 선언부는 어디있든 상관없음 버튼함수위치가 아래에있어야 해서 보통 아래에 위치함 -->
<jsp:include page="../common/footer.jsp"></jsp:include>



<!-- -----------------------------
선생님꺼
$(document).on("click", "#cmtSubmit", function(e) {
      e.preventDefault();
      write_comment(total);
   });
$(document).on("click", ".cmtMod", function(){
      let div = $(this).closest("div");
      let cmtVal = div.find("textarea").val();
      let cnoVal = div.data("cno");
      let curr_page = $(".pagination li.active a").attr("href");
      let info = {cno: cnoVal, pno: pno, content: cmtVal, pgIdx: curr_page};
      modify_comment(info);
   });
   $(document).on("click", ".cmtRm", function(){
      let div = $(this).closest("div");
      let cnoVal = div.data("cno");
      let curr_page = $(".pagination li.active a").attr("href");
      let info = {cno: cnoVal, pno: pno, pgIdx: curr_page};
      remove_comment(info);
   });
   $(document).on("click", ".pagination li a", function(e){
      e.preventDefault();
      list_comment(pno, $(this).attr("href"));
   });
function remove_comment(info) {
   $.ajax({
      url: "/comment/"+info.cno,
      type: "delete"
   }).done(function(result) {
      alert("댓글 삭제 성공~");   
   }).fail(function() {
      alert("댓글 삭제 오류!");
   }).always(function() {
      list_comment(info.pno, info.pgIdx);
   });
}
function modify_comment(info){
   $.ajax({
      url: "/comment/"+info.cno,
      type: "put",
      data: JSON.stringify(info),
      contentType: "application/json; charset=utf-8"      
   }).done(function(result) {
      alert("댓글 수정 완료~");
   }).fail(function() {
      alert("댓글 수정 오류!");
   }).always(function() {
      list_comment(info.pno, info.pgIdx);
   });   
}
function print_pagination(total, pgIdx){ // total: pno의 전체 댓글 갯수
   let pgn = '';
   let lastPage = Math.ceil(pgIdx/10.0)*10;
   let firstPage = lastPage-9;
   let prev = firstPage != 1;
   let next = false;
   
   if(lastPage*10 >= total){
      lastPage = Math.ceil(total/10.0);
   }else{
      next = true;
   }
   
   if(prev){
      pgn += '<li class="page-item"><a class="page-link" href="'+(firstPage-1)+'">';
      pgn += '<i class="fa fa-angle-double-left"></i></a></li>';
   }
   
   for (var i = firstPage; i <= lastPage; i++) {
      let isActive = pgIdx == i ? "active": "";
      pgn += '<li class="page-item ' + isActive + '">';
      pgn += '<a class="page-link" href="' + i + '">' + i + '</a></li>';
   }
   
   if(next){
      pgn += '<li class="page-item"><a class="page-link" href="'+(lastPage+1)+'">';
      pgn += '<i class="fa fa-angle-double-right"></i></a></li>';
   }
   $(".pagination").html(pgn).trigger("create");
}
function print_list(result, pgIdx) { // result == total, clist
   let clist = result.clist;
   let total = result.total;
   console.log(clist);
   console.log(total);
   
   if(clist.length == 0)
      return;
   
   let listZone = $("#accordion");
   listZone.empty();
   for (var i = 0; i < clist.length; i++) {
      let card = '<div class="card">';
      card += '<div class="card-header">';
      card += '<span>'+clist[i].writer+'</span>';
      card += '<span>'+clist[i].content+'</span>';
      card += '<span>'+clist[i].moddate+'</span>';
      if(ses_email == clist[i].writer) {
         card += '<a class="card-link" data-toggle="collapse" href="#cmt'+clist[i].cno+'">';
         card += '<i class="fa fa-cog"></i></a>';
         card += '</div><div id="cmt'+clist[i].cno+'" class="collapse" data-parent="#accordion">';
         card += '<div class="card-body"><div data-cno="'+clist[i].cno+'">';
         card += '<button type="button" class="btn btn-sm btn-warning cmtMod"><i class="fa fa-refresh"></i></button>';
         card += '<button type="button" class="btn btn-sm btn-danger cmtRm"><i class="fa fa-close"></i></button>';   
         card += '<textarea rows="3" class="form-control" required>'+clist[i].content+'</textarea></div></div>';
      }
      card += '</div></div>';
      listZone.append(card);      
   }
   print_pagination(total, pgIdx);
}
function list_comment(pno, pageIdx) {
   let pgIdx = pageIdx > 1 ? pageIdx : 1;   
   $.getJSON("/comment/pno/"+pno+"/"+pgIdx+".json",
         function(result) { // result: CommentDTO
            console.log(result);
            total = result.total; // 전역변수로 저장
            print_list(result, pgIdx);
         }
   ).fail(function(jqxhr, textStatus, error) {
      alert("댓글 리스트 로딩 오류!");
       var err = textStatus + ", " + error;
       console.log( "Request Failed: " + err );
   });
}
function write_comment(total) {
   let content = $("#cmtInput").val();
   let lastPageIdx = Math.ceil(total/10.0);
   if(content == null || content ==''){
      alert("댓글 내용을 입력해주세요!");
      return false;
   }else{
      let cmtData = {pno: pno, writer: ses_email, content: content};
      $.ajax({
         url: "/comment/write",
         type: "post",
         data: JSON.stringify(cmtData),
         contentType: "application/json; charset=utf-8"
      }).done(function(result) {
         alert("댓글 등록 완료~");
         list_comment(pno,lastPageIdx);
      }).fail(function() {
         alert("댓글 등록 오류!");
      }).always(function() {
         $("#cmtInput").val("");
      });
   }
}
    -->
