<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/entries.css"/> "/>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/navBar.css"/> "/>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/customAlert.css"/> "/>
	<head>
		<title>Budget Application</title>
		
		<script src="https://d3js.org/d3.v4.min.js"></script>
		<script src="<c:url value="/resources/js/jquery-3.0.0.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/tablesorter/jquery-latest.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/tablesorter/jquery.tablesorter.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/entries.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/navBar.js"/>"></script>
	</head>

	<body>
		<sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
			<div class="alert" id="alert">
				<span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
			  	<div id="message"></div>
			</div>
			<ul id="navBar">
				<li><a href="home" class="active">Home</a></li><!-- 
			 --><li><a href="user" class="active">Profile</a></li><!-- 
			 --><li><a href="budget" class="active">Budget</a></li><!--
			 --><li class="selected"><a href="entries" class="active">Entries</a></li><!--
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
			<h1>Budget Table</h1><br>
			<form id="buttons">
			 	Category: <select id="categories">
			 		<option></option>
			 		</select>
			 	Amount: <input type="number" name="amountField" step=".01" required/>
			 	<br><br>
				<button type="submit" id="add" onclick="addEntry()">Add</button>
				<button type="button" id="delete" onclick="deleteEntry()">Delete</button>
				<button type="submit" id="edit" onclick="editEntry()">Modify</button>
				<button type="button" id="clear">Clear</button>
			</form><br>
			<table id="budgetTable" cellspacing="0" style="width:50%" align="center" class="tablesorter">
				<thead>
					<tr>
						<th>ID</th>
						<th>Category</th>
						<th>Amount</th>
						<th>Inserted By</th>
						<th>Inserted On</th>
					</tr>
				</thead>
				
				<tbody></tbody>
			</table>
		</sec:authorize>
	</body>
</html>