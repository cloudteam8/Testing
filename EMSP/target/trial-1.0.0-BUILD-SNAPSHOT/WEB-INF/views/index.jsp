<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EMS - Login</title>
<link rel="stylesheet" type="text/css" href="../../resources/css/header.css">
<link rel="stylesheet" type="text/css" href="../../resources/css/login.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
</head>
<body>
<%@include file="header.jsp" %>
	<div class="login">
		<form action="loginuser.htm" commandName="person" method="POST">
			<div><h2 id="member">Members Area</h2></div>
			<div><h2 id="errorMessage"><c:out value="${message}"/></h2></div>
			<div>
				EMAIL ADDRESS <input type="email" path="emailAddress" required="required" class="inputArea" id="username" name="username" onchange="checkEmail()"/>
				PASSWORD <input type="password" path="password" required="required" class="inputArea" id="password" name ="password" onchange="checkPassword()"/>
				<div>
					<input type="submit" value="Login" id="submitButton"/>
				</div>
			</div>
		</form>
	</div>
	
	<script>
		function checkEmail(){
			var x = document.getElementById("username").value;
		    var txt = x.replace(/[^A-Za-z0-9;@;.]/g,"");
		    document.getElementById("username").value = txt;
		}
		
		function checkPassword(){
			var x = document.getElementById("password").value;
		    var txt = x.replace(/[^A-Za-z0-9\s]/g,"");
		    document.getElementById("password").value = txt;
		}
	</script>
	
</body>
</html>