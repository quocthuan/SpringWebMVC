<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@page session="true"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/main.css" />" >

<title>Login Page</title>
<style>

</style>
</head>
<body onload='document.loginForm.username.focus();'>
	<div id="login-box">
		<h3>Login</h3>
		
		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>

		<form name="loginForm" action="<c:url value='/j_spring_security_check' />" method="POST">
			Username:<input type="text" name="username" /> <br />
			Password:<input type="password" name="password" /> <br /> <br />
			<div align="center">
				<input type="submit" value="Login">
			</div>

			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
	</div>
</body>
</html>