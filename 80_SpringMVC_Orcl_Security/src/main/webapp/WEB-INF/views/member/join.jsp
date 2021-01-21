<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<jsp:include page="../common/header.jsp"></jsp:include>

<jsp:include page="../common/nav.jsp"></jsp:include>

<form method="post">
<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
  <div class="form-group">
    <label for="email">Email address:</label>
    <input type="email" class="form-control" placeholder="Enter email" id="email" name="email">
    <button type="button" id="check">중복확인</button>
  </div>
  <div class="form-group">
    <label for="pwd">Password:</label>
    <input type="password" class="form-control" placeholder="Enter password" id="pwd" name="pwd">
  </div>
   <div class="form-group">
    <label for="nickname">Nickname:</label>
    <input type="text" class="form-control" placeholder="Enter Nickname" id="nick" name="nickname">
  </div>
  <div class="form-group form-check">
    <label class="form-check-label">
      <input class="form-check-input" type="checkbox"> Remember me
    </label>
  </div>
  <button type="submit" class="btn btn-primary">Submit</button>
</form>
<script>
$(function() {
	$("#check").on("click",function(e){
		//e.preventDefault();
		let emailVal = $("#email").val();
		let header = $("meta[name='_csrf_header']").attr("content");
		let token = $("meta[name='_csrf']").attr("content");
		$.ajax({
			url: "/member/check",
			type: "post",
			data: {email: emailVal},
			beforeSend: function(xhr){
				xhr.setRequestHeader(header, token);
			}
			
		}).done(function(result) {
			console.log(result);
			if (result =="0") {
				$("#pwd").focus();
				alert("사용가능한 이메일입니다.")
				
			}else{
				$("#email").val("");
				$("#email").focus();
				alert("사용 불가능한 이메일입니다.")
			}
		}).fail(function(xhr, textStatus, err) {
			console.log(xhr);
			console.log(textStatus);
			console.log(err);
		}).always(function() {
			
		});
	})
});
</script>

<jsp:include page="../common/footer.jsp"></jsp:include>