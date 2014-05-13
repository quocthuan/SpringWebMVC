<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@page session="true"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%-- <link type="text/css" rel="stylesheet" href="<c:url value='/style.css' />" > --%>
<title>Login Page</title>
<style>
#login-box {
	align: center;
	width: 250px;
	padding: 20px;
	margin: 100px auto;
	background: #fff;
	-webkit-border-radius: 10px;
	-moz-border-radius: 10px;
	border: 1px solid #000;
}
</style>
</head>
<body>
	<%-- <c:if test="${'fail' eq param.auth}">
    	<div style="color:red">
        	Login Failed!!!<br />
            Reason : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
    </c:if> --%>
    
	<div id="login-box">
		<h2>Login</h2>
		<form name="loginForm" action="j_spring_security_check" method="post">
			Username:<input type="text" name="username"> <br />
			Password:<input type="password" name="password"> <br /> <br />
			<div align="center">
				<input type="submit" value="Submit">
			</div>
			
		</form>

		<%-- <%
         	String errorString = (String)request.getAttribute("error");
         	if(errorString != null && errorString.trim().equals("true")) {
           		out.println("Incorrect login name or password. Please retry using correct login name and password.");
         	}
         %> --%>
	</div>
</body>
</html>