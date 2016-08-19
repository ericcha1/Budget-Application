<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/modifyUser.css"/> "/>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/navBar.css"/> "/>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/customAlert.css"/> "/>
      
	<head>
		<title>Budget Application</title>
		
		<script src="<c:url value="/resources/js/jquery-3.0.0.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/tablesorter/jquery-latest.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/tablesorter/jquery.tablesorter.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/customAlert.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/modifyUser.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/navBar.js"/>"></script>
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
				    	 --><li><a href="category">Budget Categories</a></li>
				    </ul></li></sec:authorize><!--
			 --><li class="userLogged">Total: $${total} &nbsp&nbsp&nbsp Logged in as <sec:authentication property="principal.username" />
				<li><a href="<c:url value="/j_spring_security_logout" />" class="active">Logout</a></li>
			</ul>
			<br><br>
			<h1>User Table</h1>
			<form id="buttons">
			 	Username: <input type="text" name="usernameField" required/>
			 	Password: <input type="text" name="passwordField" required/>
			 	Name: <input type="text" name="nameField" required/>
			 	Role: <input type="text" name="roleField" required/>
			 	Enabled: <input type="text" name="enabledField" required/>
			 	Email: <input type="text" name="emailField" required/>
			 	<br><br>
				<button type="submit" id="add" onclick="addUser()">Add</button>
				<button type="button" id="delete" onclick="deleteUser()">Delete</button>
				<button type="submit" id="edit" onclick="editUser()">Modify</button>
				<button type="button" id="clear">Clear</button>
			</form>
			<table id="userTable" cellspacing="0" style="width:50%" align="center" class="tablesorter">
				<thead>
					<tr>
						<th>Username</th>
						<th>Password</th>
						<th>Name</th>
						<th>Role</th>
						<th>Enabled</th>
						<th>Email</th>
					</tr>
				</thead>
				
				<tbody></tbody>
			</table>
		</sec:authorize>
	</body>
</html>