<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Contact List</title>
</head>
<body>
	<h2>Contact list:</h2>
	<table align="center width="40%" cellpadding="5" bordercolor="#000066"
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
			</tr>
		
		</c:forEach>

	</table>
</body>
</html>