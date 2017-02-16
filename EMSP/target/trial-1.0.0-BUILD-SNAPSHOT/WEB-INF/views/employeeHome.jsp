<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@page session="true" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Home</title>
<link rel="stylesheet" type="text/css" href="resources/css/header.css">
<link rel="stylesheet" type="text/css" href="resources/css/employeeHome.css">
	
</head>
<body>
<%@include file="header.jsp" %>

		<div class="headingDiv">
			<form:form><span class="rightHeadingDiv">Hello <c:out value="${sessionScope.person.firstName }"/> [<a href="logout.htm">Logout</a>]</span></form:form>
		</div><!-- headingDiv ends here-->		
		<div>
	<div class="leftContactDetails">
	<div><h2 id="errorMessage"><c:out value="${message}"/></h2></div>
	<div class="taskHeader">Your Details</div>
				<div class="formContactDetails">
				<form method="POST">
					<table>
						<tr>
							<td>EMPLOYEE ID</td>
							<td><input type="number" value="${sessionScope.person.empID}" class="personVal" disabled="disabled"/></td>
						</tr>
						
						<tr>
							<td>FIRST NAME</td>
							<td><input type="text" id="fname" value="${sessionScope.person.firstName}" class="personVal" required="required"/></td>
						</tr>
						
						<tr>
							<td>LAST NAME</td>
							<td><input type="text" id="lname" value="${sessionScope.person.lastName}" class="personVal"/></td>
						</tr>
						
						<tr>
							<td>EMAIL ADDRESS</td>
							<td><input type="email" value="${sessionScope.person.emailAddress}" class="personVal" disabled="disabled"/></td>
						</tr>
						
						<tr>
							<td>PASSWORD</td>
							<td><input type="text" value="${sessionScope.person.password}" class="personVal"/></td>
						</tr>
						
						<tr>
							<td>PHONE NUMBER</td>
							<td><input type="number" value="${sessionScope.person.phoneNumber}" class="personVal"/></td>
						</tr>
						
						<tr>
							<td>DESIGNATION</td>
							<td><input type="text" value="${sessionScope.person.designation}" class="personVal" disabled="disabled"/></td>
						</tr>
						
						<tr>
							<td>STREET</td>
							<td><input type="text" value="${sessionScope.person.streetName}" class="personVal"/></td>
						</tr>
						
						<tr>
							<td>CITY</td>
							<td><input type="text" value="${sessionScope.person.city}" class="personVal"/></td>
						</tr>
						
						<tr>
							<td>STATE</td>
							<td><input type="text" value="${sessionScope.person.state}" class="personVal"/></td>
						</tr>
						
						<tr>
							<td>ZIPCODE</td>
							<td><input type="number" value="${sessionScope.person.zipCode}" class="personVal"/></td>
						</tr>
					</table>
					<input type="submit" value="UPDATE DETAILS" onclick="editDetails()" style="float:left" class="searchButton"/>
					<c:set var="displayValue" value="${showValue}"/>
					<c:if test="${displayValue > 1}">
						<input type="submit" value="VIEW PERFORMANCE" onclick="form.action='feedback.pdf'" style="float:right" class="searchButton"/>
					</c:if>
					</form>
				</div> <!-- formContactDetails ends here-->
			</div> <!-- leftContactDetails ends here-->
	
		
		
		<div class="rightTaskDiv">
		<form:form commandName="employee" action="" method="POST">
				<div class="taskHeader">Below are the tasks assigned to you</div><br/>
				<div class="scrollLimit">
				<table>
					<tr>
						<th class="bacColor">TASK ID</th>
						<th class="bacColor">TASK NAME</th>
						<th class="bacColor">CURRENT STATUS</th>
						<th class="bacColor">EMPLOYEE STATUS</th>
						<th class="bacColor">SUPERVISOR COMMENTS</th>
					</tr>
					
					<c:forEach var="tas" items="${taskList}" varStatus="i">
						<tr id="rowID${i.index}">
							<td><input type="text" value="${tas.taskID}" class="task${i.index}" readonly="readonly"/></td>
							<td><input type="text" value="${tas.taskName}" class="task${i.index}" disabled="disabled"/></td>
							<td><input type="text" value="${tas.currentStatus}" class="task${i.index}"/></td>
							<td><input type="text" value="${tas.employeeComments}" class="task${i.index}"/></td>
							<td><input type="text" value="${tas.supervisorComment}" class="task${i.index}" disabled="disabled"/></td>
							<td><input type="submit" value="Update" onclick="updateTask(${i.index})" class="searchButton"/></td>
						</tr>
					</c:forEach>
				</table>
				</div>		
				</form:form>
				
			<p></p>
			<br/>
			<div class="taskHeader">Below are the leaves you requested</div>
			<div class="scrollLimit">
				<table>
					<tr>
						<th class="bacColor">LEAVE ID</th>
						<th class="bacColor">START DATE</th>
						<th class="bacColor">END DATE</th>
						<th class="bacColor">APPROVAL STATUS</th>
						<th class="bacColor">REJECT REASON</th>
					</tr>
				
					<c:forEach var="lea" items="${leaveList}">
						<tr>
							<td>${lea.leaveID}</td>
							<td>${lea.leaveStartDate}</td>
							<td>${lea.leaveEndDate}</td>
							<td>${lea.approvalStatus}</td>
							<td>${lea.rejectReason}</td>
						</tr>
					</c:forEach>	
				</table>		

				</div>
			</div> <!-- rightTaskDiv ends here-->
		</div>

	<div class="requestLeaves">
		
		<div class="taskHeader">Request Leaves</div><br/>
			<form:form action="leaves.htm" commandName="leaves" method="POST">
				<div>
					START DATE<form:input type="date" path="leaveStartDate" class="inputArea" id ="leaveStartDate" required="required" onfocusout="checkStartDate()"/>
					END DATE<form:input type="date" path="leaveEndDate" class="inputArea" id ="leaveEndDate" required="required" onfocusout="checkEndDate()"/>
					<div>
						<input type="submit" value="Request Leaves" class="searchButton" id="leaveButton"/>
					</div> 
				</div>
			</form:form>
			
			<div id="info" style="color: green;"></div>
		</div>
		
		<div class="requestLeaves">
		
		<div class="taskHeader">Create Tasks</div><br/>
			<form:form action="tasks.htm" commandName="tasks" method="POST">
				<div>
					TASK NAME<form:input type="text" path="taskName" class="inputArea" id ="taskName" required="required"/>
					TASK STATUS<form:input type="text" path="currentStatus" class="inputArea" id ="currentStatus"  value = "Yet to Start" required="required"/>
					EMPLOYEE COMMENTS<form:input type="text" path="employeeComments" class="inputArea" id ="employeeComments" required="required"/>
					<div>
						<input type="submit" value="Create Task" class="searchButton"/>
					</div> 
				</div>
			</form:form>
			
			<div id="info" style="color: green;"></div>
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
	
	function ajaxPost(){
		var startDate = $("#leaveStartDate").val();
		var endDate = $("#leaveEndDate").val();
		
		$.ajax({
			type:POST,
			url:leaves.htm,
			data: "leaveStartDate=" + startDate + "&leaveEndDate=" + endDate,
			
			success:function(response){
				$('#info').html(response);
			}
		});
	}
	
		function showPDF(){
			if (xmlHttp === null)
            {
                alert("Your browser does not support AJAX!");
                return;
            }
			
			xmlHttp.open("POST","feedback.pdf",true);
			xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xmlHttp.send();
            return true;
		}
		
		function editDetails(){
			if (xmlHttp === null)
            {
                alert("Your browser does not support AJAX!");
                return;
            }
			
			var items = document.getElementsByClassName("personVal");
			var empID = items[0].value;
			var fname = items[1].value;
			var lname = items[2].value;
			var password = items[4].value;
			var phn = items[5].value;
			var street = items[7].value;
			var city = items[8].value;
			var state = items[9].value;
			var zip = items[10].value;
			
			var query = "empID="+empID+"&fname="+fname+"&lname="+lname+"&password="+password+"&phn="+phn+"&street="+street+"&city="+city+"&state="+state+"&zip="+zip;
			
			xmlHttp.onreadystatechange = function stateChanged(){
                if(xmlHttp.readyState==4){
                    var person = JSON.parse(xmlHttp.responseText);
					document.getElementById("info").innerHTML = person.lastName;
                }
            }
			
			xmlHttp.open("POST","updateEmployeeDetails.htm",true);
			xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xmlHttp.send(query);
            return false;
		}
		
		
		
		function updateTask(i){
			if (xmlHttp === null)
            {
                alert("Your browser does not support AJAX!");
                return;
            }
			
			var count = "task"+i;
			var items = document.getElementsByClassName(count);
			
			var taskID = items[0].value;
			var taskStatus  = items[2].value 
			var employeeComment = items[3].value;
			
			var query = "taskID="+taskID+"&taskStatus="+taskStatus+"&employeeComment="+employeeComment
			
			xmlHttp.open("POST","updateEmployeeTask.htm",true);
			xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xmlHttp.send(query);
            return false;
		}
		
		function checkStartDate() {
            var fromDate = document.getElementById("leaveStartDate").value; //for javascript
			var buttonVisible = document.getElementById("leaveButton");
			var toDate = document.getElementById("leaveEndDate");

            var today = new Date();

            if (Date.parse(fromDate) > Date.parse(today)) {
                buttonVisible.disabled=false;
				toDate.disabled=false;
            }
            else {
                buttonVisible.disabled=true;
				toDate.disabled=true;
            }
        }

		function checkEndDate(){
			var fromDate = document.getElementById("leaveStartDate").value;
			var toDate = document.getElementById("leaveEndDate").value;
			var buttonVisible = document.getElementById("leaveButton");

			if(Date.parse(fromDate) > Date.parse(toDate)){
   				buttonVisible.disabled=true;
			}
			else{
   				buttonVisible.disabled=false;
			}
	}
		
</script>
</body>
</html>