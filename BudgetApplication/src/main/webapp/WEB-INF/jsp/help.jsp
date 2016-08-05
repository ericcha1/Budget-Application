<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/about.css"/> "/>
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/navBar.css"/> "/>
		<script type="text/javascript" src="<c:url value="/resources/js/navBar.js"/>"></script>
	<head>
		<title>Budget Application</title>
	</head>

	<body>
		<sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
			<h1>Help</h1>
			
			<ul id="navBar">
				<li><a href="home" class="active">Home</a></li>
				<li><a href="user" class="active">Profile</a></li>
				<li><a href="budget" class="active">Budget</a></li>
				<li class="selected"><a href="help" class="active">Help</a></li>
				<li><a href="about" class="active">About</a></li>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<li class="dropdown"><a href="javascript:void(0)" class="dropButton" onclick="showAdminMenu()">Admin</a>
				    <ul class="dropdown-content" id="adminMenu">
				    	<li><a href="modifyUser">Modify User</a></li>
				    </ul></li>
				</sec:authorize>
				<li class="userLogged">Logged in as <sec:authentication property="principal.username" />
				<li><a href="<c:url value="/j_spring_security_logout" />" class="active">Logout</a></li>
			</ul>

		</sec:authorize>
	</body>
</html>