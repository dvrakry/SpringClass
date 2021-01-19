$(document).on("click", "#cmtSubmit", function(e) {
		e.preventDefault();
		write_comment(total); //펑션(다른jsp파일에 함수만 따로 저장한걸 불러오는거임), 229번의 토탈값을 받아옴
	});
	
$(document).on("click", ".cmtMod", function() {
			let div = $(this).closest("div");
			let cmtVal = div.find("textarea").val(); //find는 자식찾기
			//let cnoVal = div.attr("data-cno"); 아래랑똑같음(제이쿼리에서 data로 지원함)
			let cnoVal = div.data("cno");
			let curr_page = $(".pagination li.active a").attr("href");
			let info = {cno: cnoVal , pno: pno, content: cmtVal, pgIdx: curr_page };
			modify_comment(info);
		});
		
		$(document).on("click", ".cmtRm", function() {
			let div = $(this).closest("div");
			let cnoVal = div.data("cno");
			let curr_page = $(".pagination li.active a").attr("href");
			let info = {cno: cnoVal, pno:pno, pgIdx: curr_page };
			remove_comment(info);
		})
		//이벤트 핸들어
		$(document).on("click", ".pagination li a", function(e) {
			e.preventDefault();//비동기니깐 막아놔야됨 이동못하게
			list_comment(pno, $(this).attr("href")); //위에 리스트 코맨트 함수 이용해서 /comment/~ 이렇게 됨 , $(this).attr("href") 이건 i 값[(href="i")이거]
		});
		
		
function remove_comment(info) {
	$.ajax({
		url: "/comment/"+info.cno,
		type: "delete"
		
	}).done(function(result) {
		alert("댓글 삭제 성공");
	}).fail(function() {
		alert("댓글 삭제 오류");
	}).always(function() {
		list_comment(info.pno, info.pgIdx);
	});
};

function modify_comment(info){
	$.ajax({
		url: "/comment/"+info.cno,
		type: "put",
		data: JSON.stringify(info),
		contentType: "application/json; charset=utf-8"
		
	}).done(function() {
		alert("댓글수정완료")
	}).fail(function() {
		alert("댓글수정오류")
	}).always(function() {
		list_comment(info.pno, info.pgIdx);
	});
}

function print_pagination(total, pgIdx){// total : pno의 전체 댓글개수
	let pgn = '';
	let lastPage = Math.ceil(pgIdx/10.0)*10;
	let firstPage = lastPage - 9;
	let prev = firstPage != 1;
	let next = false;
	
	if(lastPage*10 >= total){
		lastPage = Math.ceil(total/10.0);
	}else{
		next = true;
	}
	
	if(prev){
		/* pgn += '<li class="page-item"><a class="page-link" href=/comment/pno/"'+ pno+ '/' + (firstPage-1) +'">'; */
		pgn += '<li class="page-item"><a class="page-link" href="' + (firstPage-1) +'">';
		pgn += '<i class="fa fa-angle-double-left"></i></a></li>';
	}
	
	for (var i = firstPage; i <= lastPage; i++) {
		let isActive = pgIdx==i ? "active" : "";
		pgn += '<li class="page-item ' + isActive + '">';
		pgn += '<a class="page-link" href="'+ i +'">' + i + '</a></li>';
	}
	
	if(next){
		pgn += '<li class="page-item"><a class="page-link" href="' + (lastPage+1) +'">';
		pgn += '<i class="fa fa-angle-double-right"></i></a></li>';
	}
	
	$(".pagination").html(pgn).trigger("create");
	
}

function print_list(result, pgIdx) {/* result (= DTO 의 return값으로) == total, clist 값이 들어가있음 */
	let clist = result.clist;
	let total = result.total;
	console.log(">>>>clist:" + clist);
	console.log(">>>>total:" + total);
	
	if(clist.length == 0)
		return;
	
	let listZone = $("#accordion");
	listZone.empty();
	for (var i = 0; i < clist.length; i++) {
		let card = '<div class="card">';
		card += ' <div class="card-header">';
		card += '<span>'+clist[i].writer+'</span>';
		card += '<span>'+clist[i].content+'</span>';
		card += '<span>'+clist[i].moddate+'</span>';
		if(ses_email == clist[i].writer){
			card += ' <a class="card-link" data-toggle="collapse" href="#cmt'+clist[i].cno+'"> ';
			card += '<i class="fa fa-cog"></i></a>';
			card += '</div><div id="cmt'+clist[i].cno+'" class="collapse" data-parent="#accordion">';
			card += '<div class="card-body"><div data-cno="'+clist[i].cno+'">'; // data- 이렇게 만들어낼수있음 (이렇게하면 div가 cno를 가질수있음 att로)
			card += '<button type="button" class="btn btn-sm btn-warning cmtMod"><i class="fa fa-refresh"></i></button>';
			card += '<button type="button" class="btn btn-sm btn-danger cmtRm"><i class="fa fa-close"></i></button>';
			card += '<textarea rows="3" class="form-control" required>'+clist[i].content+'</textarea></div></div>';
		}
		card += '</div></div>';
		listZone.append(card);
	}
	print_pagination(total, pgIdx); //페이지네이션
}

function list_comment(pno, pageIdx) {
	let pgIdx = pageIdx > 1 ? pageIdx : 1;
	$.getJSON("/comment/pno/"+pno+"/"+pgIdx+".json", 
			function(result) { /* 데이터를 받는 제이슨 , result에 리스트 객체가 날라옴*/
				console.log(result);
				total = result.total;
				print_list(result, pgIdx); // result = CommentDTO -컨트롤러에서 넘어옴(return 으로)
			
		}
	).fail(function(jqxhr, textStatus, error) {
		   alert("댓글리스트 로딩 오류");
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
		let cmtData = {pno: pno, writer: ses_email, content : content};
		$.ajax({
			url: "/comment/write",
			type: "post",
			data: JSON.stringify(cmtData),
			contentType: "application/json; charset=utf-8" //이렇게 보내야 일반String이 아닌 제이슨으로 날라감
			
		}).done(function(result) {
			alert("댓글 등록 완료!");
			list_comment(pno,lastPageIdx);
		}).fail(function() {
			alert("댓글 등록 오류!");
		}).always(function() {
			$("#cmtInput").val("");
		});
	}
}








/*
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
   */