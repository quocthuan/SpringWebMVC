<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Contact List</title>
<script>
	function formSubmit() {
		document.getElementById("logoutForm").submit();
	}
</script>

</head>
<body>
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="POST" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>

	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h4>
			Welcome : ${pageContext.request.userPrincipal.name} | <a href="javascript:formSubmit()"> Logout</a>
		</h4>
	</c:if>

	<h2>Contact list:</h2>
	<table align="center width=" 40%" cellpadding="5" bordercolor="#000066"
		bgcolor="#FFFFFF" border="1" cellspacing="0">
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Age</th>
			<th>Address</th>
		</tr>
		<c:forEach var="contact" items="${contactList}">
			<tr>
				<td><c:out value="${contact.contactId}" /></td>
				<td><c:out value="${contact.name}" /></td>
				<td><c:out value="${contact.age}" /></td>
				<td><c:out value="${contact.address}" /></td>
				
				<c:if test="${role eq 'ROLE_ADMIN'}">
					<td><a href='#'>Edit</a>  <a href='contacts/delete?id=${contact.contactId}'>Delete</a></td>
				</c:if>
			</tr>

		</c:forEach>
	</table>

	<c:if test="${role eq 'ROLE_ADMIN'}">
		<h4><a href="contacts/newContact">Add new</h4>
	</c:if>
</body>
</html>