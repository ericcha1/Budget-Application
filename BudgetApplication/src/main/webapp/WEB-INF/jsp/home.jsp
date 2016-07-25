<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<link rel="stylesheet" type="text/css" href="
      <c:url value="/resources/css/home.css"/> "/>
	<head>
		<title>Budget Application</title>

		<script src="<c:url value="/resources/js/jquery-3.0.0.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/tablesorter/jquery-latest.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/tablesorter/jquery.tablesorter.js" />"></script>
	</head>

	<body>
		<sec:authorize access="hasRole('ROLE_USER')">
			<h1>Home</h1>
			<h2>Welcome User</h2>
			<h3><a href="allUsers">Users</a></h3>
			<h3><a href="budget">Budget Entries</a></h3>
			<h3><a href="<c:url value="/j_spring_security_logout" />" > Logout</a></h3>
		</sec:authorize>
	</body>
</html>