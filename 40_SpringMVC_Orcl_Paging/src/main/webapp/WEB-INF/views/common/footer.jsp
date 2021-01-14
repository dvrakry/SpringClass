<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<h6 class="text-center mt-5">
Copyright 2021 by Refsnes Data. All Rights Reserved.
This Project is Powered by King.com
</h6>
</div>
<!-- .container -->
<script>
	let result = '<c:out value="${result}"/>';
	if(result != '') alert(result);
</script>
</body>
</html>