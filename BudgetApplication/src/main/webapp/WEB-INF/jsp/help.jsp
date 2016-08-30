<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/help.css"/> "/>
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
			 --><li><a href="user" class="active">Profile</a></li><!-- 
			 --><li><a href="budget" class="active">Budget</a></li><!-- 
			 --><li><a href="entries" class="active">Entries</a></li><!--
			 --><li><a href="history" class="active">History</a></li><!--
			 --><li class="selected"><a href="help" class="active">Help</a></li><!-- 
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
			<h1>Help</h1>
			<p>
				Note: Currently accounts are added by admins, though this will be modified 
				later on to allow users to create accounts.
			</p>
			<p>
				To get started, create a budget with a starting date and an ending date.
				The budget will be active for budget entries added on the starting date and
				end once the date changes to the ending date. This means that an ending
				date of 8/29/16 will not be active at 3:00pm or any other time during this
				date. To make a budget more useful, users should add limits to the various
				categories of his or her budget, so that one will know exactly when too
				much money is spent on a certain category. To view past budgets that have
				expired as well as the active budget, go to the history page. Note that
				only one budget can be active at a time, as is the purpose of setting
				limits on categories.
			</p>
			<p>
				Expenditures in one's budget should be added to the entries table on the
				Entries tab. Please correctly fill out each input to avoid any errors.
			</p>
			<p>
				Admin's have the authority to modify categories, user's budgets, and user
				data. Please contact one of them if you need any information fixed, though
				users should be able to modify most of their own information with ease. To
				find contact information for administrators, look in the User tab and you
				will see certain users marked as admins.
			</p>

		</sec:authorize>
	</body>
</html>