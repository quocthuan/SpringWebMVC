<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Generated contacts</title>
</head>
<body>
	<h3>Generated Contacts</h3>
	<table align="center width=" 40%" cellpadding="5" bordercolor="#000066"
		bgcolor="#FFFFFF" border="1" cellspacing="0">
		<tr>
			<th>Order</th>
			<th>OrgCode</th>
			<th>FacId</th>
			<th>EventTimestamp</th>
			<th>Patient data</th>
		</tr>
		<c:set var="orderNum" value="1"/>
		<c:forEach var="contact" items="${generatedContact}">
			<tr>
				<td><c:out value="${orderNum}" /></td>
				<td><c:out value="${contact.orgCode}" /></td>
				<td><c:out value="${contact.facId}" /></td>
				<td><c:out value="${contact.eventTimestamp}" /></td>
				<td><c:out value="${contact.patientData}"/></td>
				<%-- <td><c:out value="${contact.patient_datatype}" /></td> --%>
			</tr>
			<c:set var="orderNum" value="${orderNum + 1}"/>
			
		</c:forEach>
	</table>

</body>
</html>