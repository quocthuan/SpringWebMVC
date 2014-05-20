<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New contact</title>
<style>
.error {
	color: #ff0000;
}

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
</head>
<body>
	<form:form action="showContact" modelAttribute="contact" method="POST">

		<%-- <form:errors path="*" cssClass="errorblock" element="div" /> --%>

		<table>
			<tr>
				<td><form:label path="name">Name:</form:label></td>
				<td><form:input path="name" /></td>
				<%-- <td><form:errors path="name" cssClass="error" /></td> --%>
			</tr>
			<tr>
				<td><form:label path="age">Age:</form:label></td>
				<td><form:input path="age" /></td>
				<%-- <td><form:errors path="age" cssClass="error" /></td> --%>
			</tr>
			<tr>
				<td><form:label path="address">Address:</form:label></td>
				<td><form:input path="address" /></td>
				<%-- <td><form:errors path="address" cssClass="error" /></td> --%>
			</tr>

			<%-- <form:hidden path="secretValue" /> --%>

			<tr>
				<c:choose>
					<c:when test="${editMode}">
						<td colspan="3"><input type="submit" value="Update contact"/></td>
					</c:when>
					<c:otherwise>
						<td colspan="3"><input type="submit" value="Add contact"/></td>
					</c:otherwise>
				</c:choose>
			</tr>
		</table>

	</form:form>
</body>
</html>