<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/budget.css"/> "/>
      
	<head>
		<title>Budget Application</title>
		
		<script src="<c:url value="/resources/js/jquery-3.0.0.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/tablesorter/jquery-latest.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/tablesorter/jquery.tablesorter.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/budget.js"/>"></script>
	</head>

	<body>
		<sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
		
			<h1>Budget Table</h1>
<!-- 			<h3><a href="/newContact">New Contact</a></h3> -->
			<ul id="navBar">
				<li><a href="home" class="active">Home</a></li>
				<li><a href="user" class="active">Profile</a></li>
				<li class="selected"><a href="budget" class="active">Budget</a></li>
				<li><a href="help" class="active">Help</a></li>
				<li><a href="about" class="active">About</a></li>
				<li><a>Logged in as <sec:authentication property="principal.username" /></a>
				<li><a href="<c:url value="/j_spring_security_logout" />" class="active">Logout</a></li>
			</ul>
			<br><br><br>
			<form>
			 	Username: <input type="text" name="usernameField" required/>
			 	Category: <input type="text" name="categoryField" required/>
			 	Amount: <input type="number" name="amountField" required/>
			 	<br><br>
				<button type="submit" id="add" onclick="addEntry()">Add</button>
				<button type="submit" id="delete" onclick="deleteEntry()">Delete</button>
				<button type="submit" id="edit" onclick="editEntry()">Modify</button>
				<button type="button" id="clear">Clear</button>
			</form>
			<table id="budgetTable" cellspacing="0" style="width:50%" align="center" class="tablesorter">
				<thead>
					<tr>
						<th>ID</th>
						<th>Username</th>
						<th>Category</th>
						<th>Amount</th>
					</tr>
				</thead>
				
				<tbody></tbody>
			</table>
		</sec:authorize>
	</body>
</html>