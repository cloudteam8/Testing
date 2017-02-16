<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="resources/css/searchResult.css">
<link rel="stylesheet" type="text/css" href="resources/css/header.css">
<title>Administrative Services</title>
</head>


<body>
<%@include file="header.jsp" %>
<form:form><span class="rightHeadingDiv">Hello <c:out value="${sessionScope.person.firstName }"/> [<a href="logout.htm">Logout</a>]</span></form:form>
	<div class="taskHeader">
	<form:form method="POST" commandName="person">
	<div><h2 id="member">Here is the user you were searching</h2></div>
		<table>
			<tr>
				<th  class="bacColor">EMPLOYEE ID</th>
				<th class="bacColor">FIRST NAME</th>
				<th class="bacColor">LAST NAME</th>
				<th class="bacColor">EMAIL ADDRESS</th>
				<th class="bacColor">DESIGNATION</th>
				<th class="bacColor">ROLE</th>
			</tr>
			
			<tr>
				<td><form:input type="text" path="empID" value="${requestScope.person.empID}" class="personVal" readonly="true"/></td>
				<td><form:input type="text" path="firstName" value="${requestScope.person.firstName}" class="personVal" disabled="true"/></td>
				<td><form:input type="text" path="lastName" value="${requestScope.person.lastName}" class="personVal" disabled="true"/></td>
				<td><form:input type="text" path="emailAddress" value="${requestScope.person.emailAddress}" class="personVal" disabled="true"/></td>
				<td><form:input type="text" path="designation" value="${requestScope.person.designation}" class="personVal" disabled="true"/></td>
				<td><form:input type="text" path="userRole" value="${requestScope.person.userRole}" class="personVal"/></td>
			</tr>
		</table>
		<input type="submit" value="Delete User" onclick="deleteUser()" class="searchButton"/>
		<input type="submit" value="Update User Persmissions" onclick="form.action='userRole'" class="searchButton"/>
	</form:form>
	</div>
	<script>
	
	var xmlHttp;
    xmlHttp = GetXmlHttpObject();
	
	function GetXmlHttpObject()
    {
        var xmlHttp = null;
        try
        {
            // Firefox, Opera 8.0+, Safari
            xmlHttp = new XMLHttpRequest();
        } catch (e)
        {
            // Internet Explorer
            try
            {
                xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
            } catch (e)
            {
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
            }
        }
        return xmlHttp;
    }
	
	
	
		function deleteUser(){
			
			if (xmlHttp === null)
            {
                alert("Your browser does not support AJAX!");
                return;
            }
			
			var items = document.getElementsByClassName("personVal");

			var empID = items[0].value;

			
			var query = "empID="+empID

			xmlHttp.open("POST","deleteUser.htm",true);
			xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xmlHttp.send(query);
            return false;
		}
	</script>
</body>
</html>