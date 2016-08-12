<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<script src="<c:url value="/resources/js/jquery-3.0.0.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/forgotPassword.js"/>"></script>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/forgotPassword.css"/> "/>
<head>
	<title>Forgotten Username or Password</title>
</head>
<body>
	<h1>Forgot your info?</h1>
	<h2>${message}</h2>
	<br>
	<form>
		Enter in your email: <input type="text" name="emailField"/>
		<button type="button" id="sendEmail" onclick="resetPass()">Send</button>
	</form>	
	
	<a href="<c:url value="login" />">Login</a>

</body>
</html>