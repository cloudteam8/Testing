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
<title>Manager Home</title>
<link rel="stylesheet" type="text/css" href="resources/css/header.css">
<link rel="stylesheet" type="text/css" href="resources/css/managerHome.css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
<style>
	#showForm{
	display:none;
	}
</style>
</head>
<body>
	<%@include file="header.jsp" %>
	<div class="headingDiv">
		<form:form><span class="rightHeadingDiv">Hello <c:out value="${sessionScope.person.firstName }"/> [<a href="logout.htm">Logout</a>]</span></form:form>
	</div><!-- headingDiv ends here-->
	
	<span class="taskHeader">Employee </span><br/>
	<table>
		<c:forEach var="emp" items="${employeeList}">
		<tr>
			<td>
				<form:form action="employeede.htm" commandName="employee" method="POST">
					<input type="text" value="${emp.firstName} ${emp.lastName}" disabled="disabled"/>
					<form:input type="text" path="empID" value="${emp.empID}" readonly="true"/>
					<input type="submit" value="View Details" class="searchButton"/>
				</form:form>
			</td>
		</tr>
		</c:forEach>
	</table>
	
	<c:set var="displayValue" value="${reqList}"/>
	<c:if test="${not empty  displayValue}">
			<form:form commandName="workRequests" action="" method="POST">

		<span class="taskHeader">Below are the requests raised by you which are not yet resolved</span><br/>
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
				<td><input type="text" value="${req.managerComments}" class="inputArea ${i.index}"/></td>
				<td><input type="text" value="${req.adminComments}" class="inputArea ${i.index}" disabled="disabled"/></td>
				<td><input type="submit" value="Update" onclick="updateRequest(${i.index})" class="searchButton"/></td>
			</tr>
			</c:forEach>	
		</table>		
	</form:form>
	</c:if>
	
	<button id="showCreateForm">Create a new Request</button>
	
	<div class="register" id="showForm">
	<form:form action="createEmployeeRequests.htm" commandName="workRequests" method="POST">
	<span class="taskHeader">Request Form For Employee Data Creation</span><br/>
	<div>
		FIRST NAME <form:input type="text" class="inputArea" placeholder="Employee First Name" path="firstName" name ="firstname" id="firstname" required="required"/>
		LAST NAME <form:input type="text" class="inputArea" placeholder="Employee Last Name" path="lastName" name ="lastname" id="lastname" required="required"/>
		EMPLOYEE NUMBER <form:input type="number" class="inputArea" placeholder="Employee ID" path="employeeID" name ="empid" id="empid" required="required"/>
		EMAIL ADDRESS <form:input type="email" class="inputArea" placeholder="Employee Office email address" path="emailAddress" name ="emailid" id="emailid" required="required"/>
		DESIGNATION <form:input type="text" class="inputArea" placeholder="Employee Designation" path="designation" name ="designation" id="designation" required="required"/>
		ROLE <br/><form:radiobutton path="userRole" name="role" value="ADMIN" id="adminRole" class="rado"/>ADMIN<br/>
			<form:radiobutton path="userRole" name="role" value="EMPLOYEE" id="employeeRole" checked="checked" class="rado"/> EMPLOYEE <p></p>
		MANAGER COMMENTS <form:input type="text" class="inputArea" placeholder="Comments" path="managerComments" name ="managerComments" id="managerComments"/>
		REQUEST STATUS<form:select path ="requestStatus" class="inputArea">
               					<c:forEach var ="types" items="${optionList}">
                				<form:option value ="${types}"/>
               					</c:forEach>
          				</form:select>
		
		
		<div>
			<input type="submit" value="Request" id="submitButton" class="searchButton"/>
		</div> 
	</div>
	</form:form>
	</div>
	
	
	<script>
	$(document).ready(function(){
	    $("#showCreateForm").click(function(){
	        $("#showForm").toggle();
	    });
	});
	
	
	
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
			var empID = items[1].value;
			var requestStatus = items[6].value;
			var mgrcomments = items[7].value;
			
			
			var query = "workRequestID="+workRequestID+"&requestStatus="+requestStatus+"&mgrcomments="+mgrcomments+"&empID="+empID
			
			xmlHttp.open("POST","mgrUpdateRequests.htm",true);
			xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xmlHttp.send(query);
            return false;
		}
	
	</script>
	
</body>
</html>