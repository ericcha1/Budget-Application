<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<h1>Home</h1>
		<h2>Welcome User</h2>
<!-- 		<script type="text/javascript" src="js/home.js"></script> -->
		<table id="userTable" cellspacing="0" style="width:50%" align="center" class="tablesorter">
			<thead>
				<tr>
					<th>ID</th>
					<th>Username</th>
					<th>Password</th>
					<th>Name</th>
					<th>Role</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>1</th>
					<th>ericcha1</th>
					<th>pass2</th>
					<th>Eric Cha</th>
					<th>Creater</th>			
				</tr>
				<tr>
					<th>2</th>
					<th>zzUsername</th>
					<th>123Numbers</th>
					<th>John Cha</th>
					<th>User</th>			
				</tr>
			</tbody>
		</table>
		<script>
		$(document).ready(function()
		{ 
	        $("#userTable").tablesorter(); 
	    }); 
		</script>
	</body>
</html>