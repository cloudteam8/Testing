<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
${firstname} created successfully

	<table>
	<tr>
		<td> <a href="createuser.htm">New User Registration</a> </td>
	</tr>
	
	<tr>
		<td> <a href="deleteuser.htm">Delete User</a> </td>
	</tr>
	
	<tr>
		<td> <a href="manageuser.htm">Manage User Permissions</a> </td>
	</tr>
	
	<tr>
		<td> <a href="resetuserpassword.htm">Reset Password</a> </td>
	</tr>
	</table>
</body>
</html>