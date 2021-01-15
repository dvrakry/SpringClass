<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="common/header.jsp"></jsp:include>

<jsp:include page="common/nav.jsp"></jsp:include>
<div class="jumbotron">
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
</div>
<jsp:include page="common/footer.jsp"></jsp:include>
