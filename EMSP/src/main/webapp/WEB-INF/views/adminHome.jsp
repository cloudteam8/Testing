<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page import ="com.myperson.trial.doa.RequestDAO" %>
<%@ page import ="java.util.List" %>
    
    <%
		//get the option list
	RequestDAO requestDAO = new RequestDAO();
 	List<String> optionList = requestDAO.listOptions();
 	pageContext.setAttribute("optionList",optionList); 
	%>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="resources/css/searchUser.css">
	<link rel="stylesheet" type="text/css" href="resources/css/header.css">
	<style>
		body{
			margin:0;
			paddding:0;
			background-image: url("http://www.atlaviation.com/wp-content/uploads/2014/10/fit.jpg");
			background-image: url("http://www.raffaelphoto.com/wp-content/uploads/2013/01/new-york-city-at-night-skyline-panorama1.jpg");
			
    		background-repeat: no-repeat;
    		background-size:cover;
		}
	</style>
	<title>Admin Homepage</title>
</head>


<body>
<%@include file="header.jsp" %>
<form:form><span class="rightHeadingDiv">Hello <c:out value="${sessionScope.person.firstName }"/> [<a href="logout.htm">Logout</a>]</span></form:form>
<div class="adminFunc">
	<div><h2 id="errorMessage"><c:out value="${message}"/></h2></div>
	<div><h2 id="member">Welcome ${sessionScope.person.firstName}. Below are the various options available</h2></div>
	<table>
	<tr>
		<td> <a href="createUser.htm"><button class="searchButton">New ADMIN/EMPLOYEE Registration</button></a> </td>
	</tr>
	
	<tr>
		<td> <a href="registerManager.htm"><button class="searchButton">New MANAGER Registration</button></a> </td>
	</tr>
	
	<tr>
		<td>
			<div id="searchDiv">
				<form:form id="searchForm" method="post" commandName="person" action="searchUser.htm">
		        	<form:input type="email" placeholder="Employee Office email address" path="emailAddress" name ="emailid" id="emailid" class="searchText" size="40" required="required" onchange="checkEmail()"/>
		        	<input type="submit" value="Search" class="searchButton"/>
				</form:form>
			</div>
		</td>
	</tr>
	</table>
	</div>
	
	
	<c:set var="displayValue" value="${reqList}"/>
	<c:if test="${not empty  displayValue}">
	<div class="taskHeader">
			<form:form commandName="workRequests" action="" method="POST">

		<div><h2 id="member">Below are the requests raised by you which are not yet resolved</h2></div>
		<table>
			<tr>
				<th class="bacColor">REQUEST ID</th>
				<th class="bacColor">EMPLOYEE ID</th>
				<th class="bacColor">NAME</th>
				<th class="bacColor">EMAIL ADDRESS</th>
				<th class="bacColor">DESIGNATION</th>
				<th class="bacColor">USER ROLE</th>
				<th class="bacColor">CURRENT STATUS</th>
				<th class="bacColor">MANAGER COMMENTS</th>
				<th class="bacColor">ADMIN COMMENTS</th>
			</tr>
				
			<c:forEach var="req" items="${reqList}" varStatus="i">
			<tr id="rowID${i.index}">
				<td><input type="text" value="${req.workRequestID}" class="inputArea ${i.index}" readonly="readonly"/></td>
				<td><input type="text" value="${req.employeeID}" class="inputArea ${i.index}" readonly= "readonly"/></td>
				<td><input type="text" value="${req.firstName} ${req.lastName}" class="inputArea ${i.index}" disabled="disabled"/></td>
				<td><input type="text" value="${req.emailAddress}" class="inputArea ${i.index}" disabled="disabled"/></td>
				<td><input type="text" value="${req.designation}" class="inputArea ${i.index}" disabled="disabled"/></td>
				<td><input type="text" value="${req.userRole}" class="inputArea ${i.index}" disabled="disabled"/></td>
				<td><form:select path ="requestStatus" class="inputArea ${i.index}" value="${req.requestStatus}">
               					<c:forEach var ="types" items="${optionList}">
                				<form:option value ="${types}"/>
               					</c:forEach>
          				</form:select>
				<td><input type="text" value="${req.managerComments}" class="inputArea ${i.index}" disabled="disabled"/></td>
				<td><input type="text" value="${req.adminComments}" class="inputArea ${i.index}"/></td>
				<td><input type="submit" value="Update" class="searchButton" onclick="updateRequest(${i.index})"/></td>
			</tr>
			</c:forEach>	
		</table>		
	</form:form>
	</div>
	</c:if>
	
	
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
	
		function updateRequest(i){
			if (xmlHttp === null)
            {
                alert("Your browser does not support AJAX!");
                return;
            }
			
			var items = document.getElementsByClassName(i);
			
			var workRequestID = items[0].value;
			var requestStatus = items[6].value;
			var admincomments = items[8].value;
			
			
			var query = "workRequestID="+workRequestID+"&requestStatus="+requestStatus+"&admincomments="+admincomments
			
			xmlHttp.open("POST","adminUpdateRequests.htm",true);
			xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xmlHttp.send(query);
            return false;
		}
		
		function checkEmail(){
			var x = document.getElementById("emailid").value;
		    var txt = x.replace(/[^A-Za-z0-9;@;.]/g,"");
		    document.getElementById("emailid").value = txt;
		}
		
	</script>
	
</body>
</html>