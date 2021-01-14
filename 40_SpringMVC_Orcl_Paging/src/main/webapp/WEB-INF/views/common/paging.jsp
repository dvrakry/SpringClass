<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<ul class="pagination">
	<c:if test="${pgbld.prev }">
		<li class="page-item"><a class="page-link"
		 href="/product/list?pageIdx=${pgbld.firstPageIdx-1 }&qty=${pgbld.pg.qty }"> 
		<i class="fa fa-angle-double-left"></i></a></li>
	</c:if>
	<c:forEach begin="${pgbld.firstPageIdx }" end="${pgbld.lastPageIdx }" var="i">
		<li class="page-item ${pgbld.pg.pageIdx == i ? 'active' : '' }"><a class="page-link" 
		href="/product/list?pageIdx=${i }&qty=${pgbld.pg.qty}">${i }</a></li>
	</c:forEach>
	<c:if test="${pgbld.next }">
		<li class="page-item"><a class="page-link" 
		href="/product/list?pageIdx=${pgbld.lastPageIdx+1 }&qty=${pgbld.pg.qty }"> 
		<i class="fa fa-angle-double-right"></i></a></li>
	</c:if>
</ul>




