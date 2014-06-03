<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/main.css" />" >
<link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/table.css" />" >
<title>Generated contacts</title>
</head>
<body>
	<div id="container">
		<div id="header">
			<span class="title">Generated Contacts</span> 
		</div>

		<div class="main table">
			<table>
				<tr>
					<td>Order</td>
					<td>OrgCode</td>
					<td>FacId</td>
					<td>EventTimestamp</td>
					<td>Patient data</td>
				</tr>
				<c:set var="orderNum" value="1"/>
				<c:forEach var="contact" items="${generatedContact}">
					<tr>
						<td><c:out value="${orderNum}" /></td>
						<td><c:out value="${contact.orgCode}" /></td>
						<td><c:out value="${contact.facId}" /></td>
						<td><c:out value="${contact.eventTimestamp}" /></td>
						<td><c:out value="${contact.patientData}"/></td>
					</tr>
					<c:set var="orderNum" value="${orderNum + 1}"/>
					
				</c:forEach>
			</table>
		</div>
	</div>

</body>
</html>