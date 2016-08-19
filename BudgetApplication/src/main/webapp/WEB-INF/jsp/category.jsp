<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@page session="true"%>
<html>
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/category.css"/> "/>
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/navBar.css"/> "/>
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/customAlert.css"/> "/>
	<head>
		<title>Budget Application</title>

		<script src="<c:url value="/resources/js/jquery-3.0.0.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/tablesorter/jquery-latest.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/tablesorter/jquery.tablesorter.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/navBar.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/category.js"/>"></script>
	</head>

	<body>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<div class="alert" id="alert">
				<span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
			  	<div id="message">This is an alert box.</div>
			</div>
			<ul id="navBar">
				<li><a href="home" class="active">Home</a></li><!-- 
			 --><li><a href="user" class="active">Profile</a></li><!-- 
			 --><li><a href="budget" class="active">Budget</a></li><!-- 
			 --><li><a href="help" class="active">Help</a></li><!-- 
			 --><li><a href="about" class="active">About</a></li><!-- 
			 --><sec:authorize access="hasRole('ROLE_ADMIN')"><!--
				 --><li class="dropdown"><a href="javascript:void(0)" class="dropButton" onclick="showAdminMenu()">Admin</a>
				    <ul class="dropdown-content" id="adminMenu">
				    	<li><a href="modifyUser">Modify User</a></li><!-- 
				    	 --><li class="selected"><a href="category">Budget Categories</a></li>
				    </ul></li></sec:authorize><!--
			 --><li class="userLogged">Total: $${total} &nbsp&nbsp&nbsp Logged in as <sec:authentication property="principal.username" />
				<li><a href="<c:url value="/j_spring_security_logout" />" class="active">Logout</a></li>
			</ul>
			<br><br>
			<h1>Category Table</h1>
			<form id="buttons">
				Category: <input type="text" name="categoryField" required/>
				<button type="submit" id="add" onclick="addCategory()">Add</button>
				<button type="submit" id="save" onclick="saveCategory()">Save</button>
				<button type="button" id="clear">Clear</button>
			</form>
			<br>
			<table id="categoryTable" cellspacing="0" align="center" class="tablesorter">
				<thead>
					<tr>
						<th>Category</th>
						<th>Action</th>
					</tr>
				</thead>
				
				<tbody></tbody>
			</table>

		</sec:authorize>
	</body>
</html>