<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/main.css" />" >
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
	<c:choose>
		<c:when test="${contact.contactId != 0}">
			<c:set var="actionText" scope="session" value="Update"/>
			<c:set var="actionForm" scope="session" value="update" />
		</c:when>
		<c:otherwise>
			<c:set var="actionText" scope="request" value="Add"/>
			<c:set var="actionForm" scope="request" value="add" />
		</c:otherwise>
	</c:choose>

	<div id="input-data-box">
	<form:form action="${actionForm}" modelAttribute="contact" method="POST">

		<%-- <form:errors path="*" cssClass="errorblock" element="div" /> --%>
		<h3>${actionText} contact</h3>
		<table>
			<tr>
				<td><form:label path="name">Name:</form:label></td>
				<td><form:input path="name" width="250px"/></td>
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
		</table>
		<div style="align:center">
			<c:choose>
				<c:when test="${editMode}">
					<input type="submit" value="${actionText}"/>					
				</c:when>
				<c:otherwise>
					<input type="submit" value="${actionText}"/>
				</c:otherwise>
			</c:choose>
		</div>

		<form:hidden path="contactId"/>
	</form:form>
	</div>
</body>
</html>