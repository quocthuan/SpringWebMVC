<%@page import="org.springframework.context.annotation.Import"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page session="true"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Contact List</title>

<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/resources/js/jquery.blockUI.js" />"></script>
<link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/main.css" />" >
<link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/table.css" />" >
<script type="text/javascript">

	function formSubmit() {
		document.getElementById("logoutForm").submit();
	}

	function generatedData() {
		$.blockUI({
			message : $('#dataGeneratingDialog'),
			timeout: 5000
		});
		
		$('#runBackground').click(function() { 
			 $.unblockUI(); 
        }); 

		var numberRecord = $('#numberRecord').val();
		$.get("http://" + location.host + "${pageContext.request.contextPath}/service/generatedContact?records=" + numberRecord, function(status) {
			if (!status) {
				alert ("Data generating failed. Please try again.")
			}
			
			$.unblockUI();
		});
	}
</script>

</head>
<body>
	<!-- Data generating dialog -->
	<div id="dataGeneratingDialog" style="align:center; display:none; cursor: default"> 
		<p><img width="32px" height="32px" src="${pageContext.request.contextPath}/resources/images/busy.gif" />
		</p> <p>Data generating...</p>
	
        <input type="button" id="runBackground" value="Run in background" />
	</div> 

	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="POST" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>

	<div id="container">
		<div id="header">
			<span class="float-left title">Contact list</span> 
			<span class="float-right welcome">
				<c:if test="${pageContext.request.userPrincipal.name != null}">
					Welcome : ${pageContext.request.userPrincipal.name} | <a href="javascript:formSubmit()"> Logout</a>
				</c:if>
			</span>
		
		</div>
		<div id="main">
			<c:if test="${role eq 'ROLE_ADMIN'}">
				<span class="float-left" style="margin-top: 43px">
					<ul>
						<li><a href="contacts/add">Add new contact</a></li>
					</ul>
				</span> 
				
				<span class="float-right" style="margin-right: 100px">
					<ul>
						<li><a href="http://<spring:eval expression="@propertyConfigurer.getProperty('cassandra.report.host.url_seed')" />:8080/pointclickcare/parameterReport">Report generating</a></li>
						<li><a href="javascript:generatedData()">Data generating</a> &nbsp;&nbsp;<input id="numberRecord" type="text" style="width:60px" value="1000" /> records</li>
						<li><a href="contacts/generated">View generated data</a></li>
					</ul>
				</span>
			</c:if>
		</div>
		<div class="table float-left">
			<table>
				<tr>
					<td>Id</td>
					<td>Name</td>
					<td>Age</td>
					<td>Address</td>
					<c:if test="${role eq 'ROLE_ADMIN'}">
						<td>&nbsp;</td>
					</c:if>
				</tr>
				<c:forEach var="contact" items="${contactList}">
					<tr>
						<td><c:out value="${contact.contactId}" /></td>
						<td><c:out value="${contact.name}" /></td>
						<td><c:out value="${contact.age}" /></td>
						<td><c:out value="${contact.address}" /></td>
		
						<c:if test="${role eq 'ROLE_ADMIN'}">
							<td><a href='contacts/update?id=${contact.contactId}'>Edit</a>
								<a href='contacts/delete?id=${contact.contactId}'>Delete</a></td>
						</c:if>
					</tr>
				</c:forEach>
			</table>

		</div>
	</div>
</body>
</html>