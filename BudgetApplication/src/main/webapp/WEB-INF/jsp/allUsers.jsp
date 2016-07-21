<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/allUsers.css"/> "/>
      
	<head>
		<title>Budget Application</title>
		
		<script src="<c:url value="/resources/js/jquery-3.0.0.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/tablesorter/jquery-latest.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/tablesorter/jquery.tablesorter.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/user.js"/>"></script>
	</head>

	<body>
		<sec:authorize access="hasRole('ROLE_USER')">
			<h1>User Table</h1>
			<h3><a href="/newContact">New Contact</a></h3>
			<table id="userTable" cellspacing="0" style="width:50%" align="center" class="tablesorter">
				<thead>
					<tr>
						<th>ID</th>
						<th>Username</th>
						<th>Password</th>
						<th>Name</th>
						<th>Role</th>
						<th>Enabled</th>
					</tr>
				</thead>
				
				<tbody></tbody>
			</table>
			
			<h3>
				<a href="home.html">Home</a>
			</h3>
		</sec:authorize>
	</body>
</html>