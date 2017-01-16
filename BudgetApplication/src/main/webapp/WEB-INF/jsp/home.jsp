<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@page session="true"%>
<html>
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/home.css"/> "/>
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/navBar.css"/> "/>
	<head>
		<title>Budget Application</title>

		<script src="https://d3js.org/d3.v4.min.js"></script>
		<script src="<c:url value="/resources/js/jquery-3.0.0.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/tablesorter/jquery-latest.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/tablesorter/jquery.tablesorter.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/navBar.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/home.js"/>"></script>
	</head>

	<body>
		<sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
			<ul id="navBar">
				<li class="selected"><a href="home" class="active">Home</a></li><!-- 
			 --><li><a href="user" class="active">Profile</a></li><!-- 
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
			<h1>Home</h1>
			<h2 id="message">Recent Entries</h2>
			
			<table id="recentTable" cellspacing="0" style="width:40%" align="center" class="tablesorter">
				<thead>
					<tr>
						<th>Category</th>
						<th>Amount</th>
						<th>Inserted On</th>
					</tr>
				</thead>
				
				<tbody></tbody>
			</table>
			
			<script>
			
				
			
			</script>

<%-- 			<c:url value="/j_spring_security_logout" var="logoutUrl" /> --%>
			
<!-- 				csrt for log out -->
<%-- 				<form action="${logoutUrl}" method="post" id="logoutForm"> --%>
<!-- 				  <input type="hidden"  -->
<%-- 					name="${_csrf.parameterName}" --%>
<%-- 					value="${_csrf.token}" /> --%>
<!-- 				</form> -->
				
<!-- 				<script> -->
<!-- // 					function formSubmit() { -->
<!-- // 						document.getElementById("logoutForm").submit(); -->
<!-- // 					} -->
<!-- 				</script> -->
			
<%-- 				<c:if test="${pageContext.request.userPrincipal.name != null}"> --%>
<!-- 					<h3> -->
<!-- 						<a href="javascript:formSubmit()"> Logout</a> -->
<!-- 					</h3> -->
<%-- 				</c:if> --%>
		</sec:authorize>
	</body>
</html>