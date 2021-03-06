<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/about.css"/> "/>
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/navBar.css"/> "/>
	<script src="<c:url value="/resources/js/jquery-3.0.0.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/navBar.js"/>"></script>
	<head>
		<title>Budget Application</title>
	</head>

	<body>
		<sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">			
			<ul id="navBar">
				<li><a href="home" class="active">Home</a></li><!-- 
			 --><li class="selected"><a href="user" class="active">Profile</a></li><!-- 
			 --><li><a href="budget" class="active">Budget</a></li><!-- 
			 --><li><a href="entries" class="active">Entries</a></li><!--
			 --><li><a href="history" class="active">History</a></li><!--
			 --><li><a href="help" class="active">Help</a></li><!-- 
			 --><li><a href="about" class="active">About</a></li><!-- 
			 --><sec:authorize access="hasRole('ROLE_ADMIN')"><!--
				 --><li class="dropdown"><a href="javascript:void(0)" class="dropButton" onclick="showAdminMenu()">Admin</a>
				    <ul class="dropdown-content" id="adminMenu">
				    	<li><a href="modifyUser">Modify User</a></li>
				    	<li><a href="modifyBudget">Modify Budget</a></li>
				    	<li><a href="category">Budget Categories</a></li>
				    </ul></li></sec:authorize><!--
			 --><li class="userLogged">Total: $${total} &nbsp&nbsp&nbsp Logged in as <sec:authentication property="principal.username" />
				<li><a href="<c:url value="/j_spring_security_logout" />" class="active">Logout</a></li>
			</ul>
			<br><br>
			<h1>About</h1>
			<p>
				This Budget Application allows users to track their spending on various 
				categories. By keeping a running total of these expenditures, one will
				constantly be able to view the money currently being used from one's budget
				as well as the how the spendings are distributed. 
			</p>
			<p>
				This project was created for the purposes of practicing web development 
				by Eric Cha, a current computer science major studying at the University of 
				Maryland, Baltimore County. Please note that this project is still being
				updated. Graphs will be added in order to properly portray one's budget and 
				the proportions being spent in each category.
			</p>

		</sec:authorize>
	</body>
</html>