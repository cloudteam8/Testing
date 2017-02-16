<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page import ="com.myperson.trial.doa.FeedbackDAO" %>
    <%@ page import ="java.util.List" %>
    
    <%
		//get the option list
	FeedbackDAO perfFeedbackDAO = new FeedbackDAO();
 	List<String> optionList = perfFeedbackDAO.listOptions();
 	pageContext.setAttribute("optionList",optionList); 
%>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Employee Details</title>
	<link rel="stylesheet" type="text/css" href="resources/css/header.css">
	<link rel="stylesheet" type="text/css" href="resources/css/employeeHome.css">
</head>

<body>
	<%@include file="header.jsp" %>
	<div class="headingDiv">
		<form:form><span class="rightHeadingDiv">Hello <c:out value="${sessionScope.person.firstName }"/> [<a href="logout.htm">Logout</a>]</span></form:form>
	</div><!-- headingDiv ends here-->

	<form:form commandName="employee" action="" method="POST">

		<span class="taskHeader">Below is the status of the leaves the employee has requested</span><br/>
		<table>
			<tr>
				<th class="bacColor">EMPLOYEE ID</th>
				<th class="bacColor">LEAVE ID</th>
				<th class="bacColor">START DATE</th>
				<th class="bacColor">END DATE</th>
				<th class="bacColor">APPROVAL STATUS</th>
				<th class="bacColor">COMMENTS</th>
			</tr>
				
			<c:forEach var="lea" items="${leaveList}" varStatus="i">
			<tr id="rowID${i.index}">
				<td><form:input path = "empID" type="text" value="${employee.empID}" class="${i.index}" readonly="true"/></td>
				<td><input type="text" value="${lea.leaveID}" class="${i.index}" readonly="readonly"/></td>
				<td><input type="text" value="${lea.leaveStartDate}" class="${i.index}" disabled="disabled"/></td>
				<td><input type="text" value="${lea.leaveEndDate}" class="${i.index}" disabled="disabled"/></td>
				<td><input type="text" value="${lea.approvalStatus}" class="${i.index}"/></td>
				<td><input type="text" value="${lea.rejectReason}" class="${i.index}"/></td>
				<td><input type="submit" value="Save" onclick="saveLeave(${i.index})" class="searchButton"/></td>
			</tr>
			</c:forEach>	
		</table>		
	</form:form>
	
	<p></p>
	
	<form:form commandName="employee" action="" method="POST">

		<span class="taskHeader">Below is the status of the tasks assigned to the employee</span><br/>
		<table>
			<tr>
				<th class="bacColor">EMPLOYEE ID</th>
				<th class="bacColor">TASK ID</th>
				<th class="bacColor">TASK NAME</th>
				<th class="bacColor">CURRENT STATUS</th>
				<th class="bacColor">EMPLOYEE COMMENTS</th>
				<th class="bacColor">MANAGER COMMENTS</th>
			</tr>
				
			<c:forEach var="tas" items="${taskList}" varStatus="i">
			<tr id="rowID${i.index}">
				<td><form:input path = "empID" type="text" value="${employee.empID}" class="task${i.index}" readonly="true"/></td>
				<td><input type="text" value="${tas.taskID}" class="task${i.index}" readonly="readonly"/></td>
				<td><input type="text" value="${tas.taskName}" class="task${i.index}" disabled="disabled"/></td>
				<td><input type="text" value="${tas.currentStatus}" class="task${i.index}" disabled="disabled"/></td>
				<td><input type="text" value="${tas.employeeComments}" class="task${i.index}" readonly="readonly"/></td>
				<td><input type="text" value="${tas.supervisorComment}" class="task${i.index}"/></td>
				<td><input type="submit" value="Update" onclick="updateTask(${i.index})" class="searchButton"/></td>
				<td><input type="submit" value="Delete" onclick="deleteTask(${i.index})" class="searchButton"/></td>
			</tr>
			</c:forEach>	
		</table>		
	</form:form>
	
	
	<div class="requestLeaves">
		
		<span class="taskHeader">Create Tasks</span><br/>
			<form:form action="tasksManager.htm" commandName="tasks" method="POST">
				<div><h2 id="member">Create a new task for <c:out value="${employee.firstName } ${employee.lastName }"/></h2></div>
				<div>
					EMPLOYEE ID<input type="text" value="${employee.empID}"  class="inputArea" name = "empID" id ="empID" readonly="readonly"/>
					TASK NAME<form:input type="text" path="taskName" class="inputArea" id ="taskName" required="required"/>
					TASK STATUS<form:input type="text" path="currentStatus" class="inputArea" id ="currentStatus"  value = "Yet to Start" required="required"/>
					MANAGER COMMENTS<form:input type="text" path="supervisorComment" class="inputArea" id ="supervisorComment" required="required"/>
					<div>
						<input type="submit" value="Create Task" class="searchButton"/>
					</div> 
				</div>
			</form:form>
			
			<div id="info" style="color: green;"></div>
		</div>
	<p></p>
	<p></p>
		<c:set var="displayValue" value="${showValue}"/>
		<c:if test="${displayValue > 1}">
		<div class="requestLeaves"> <!-- Performance Feedback details -->
		<span class="taskHeader">Feedback</span><br/>
			<form:form action="giveFeedback.htm" commandName ="perfFeedback" method="POST">
				<div>
					EMPLOYEE ID<input type="text" value="${employee.empID}"  class="inputArea" name = "empID" id ="empID" readonly="readonly"/>
					VALUE CREATOR<form:select path ="valueCreator"  class="inputArea">
               					<c:forEach var ="types" items="${optionList}">
                				<form:option value ="${types}"/>
               					</c:forEach>
          						</form:select>
          						
          			PEOPLE DEVELOPER<form:select path ="peopleDeveloper" class="inputArea">
               					<c:forEach var ="types" items="${optionList}">
                				<form:option value ="${types}"/>
               					</c:forEach>
          						</form:select>
          						
          			BUSINESS OPERATOR<form:select path ="businessOperator" class="inputArea">
               					<c:forEach var ="types" items="${optionList}">
                				<form:option value ="${types}"/>
               					</c:forEach>
          						</form:select>
          						
          			COMMUNICATION SKILLS<form:select path ="communicationSkills" class="inputArea">
               					<c:forEach var ="types" items="${optionList}">
                				<form:option value ="${types}"/>
               					</c:forEach>
          						</form:select>
          			
          			TECHNICAL SKILLS<form:select path ="technicalSkills" class="inputArea">
               					<c:forEach var ="types" items="${optionList}">
                				<form:option value ="${types}"/>
               					</c:forEach>
          						</form:select>
          			
          			TASK COMPLETED FEEDBACK<form:select path ="taskCompletionSkills" class="inputArea">
               					<c:forEach var ="types" items="${optionList}">
                				<form:option value ="${types}"/>
               					</c:forEach>
          						</form:select>
          						
          			LEAVES FEEDBACK<form:select path ="uninformedLeavesTaken" class="inputArea">
               					<c:forEach var ="types" items="${optionList}">
                				<form:option value ="${types}"/>
               					</c:forEach>
          						</form:select>
          			EFFICIENCY AREAS STATUS<form:input type="text" path="efficientAreas" class="inputArea" id ="efficientAreas" required="required" onchange="checkeff()"/>
					AREAS TO IMPROVE<form:input type="text" path="improvementAreas" class="inputArea" id ="improvementAreas" required="required" onchange="checkimp()"/>
					
					<div>
						<input type="submit" value="Provide Feedback" class="searchButton"/>
					</div> 
				</div>
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
	
		function saveLeave(i){
			if (xmlHttp === null)
            {
                alert("Your browser does not support AJAX!");
                return;
            }
			
			var items = document.getElementsByClassName(i);
			
			var empID = items[0].value;
			var leaveID = items[1].value;
			var leaveStartDate  = items[2].value
			var leaveEndDate  = items[3].value 
			var approvalStatus = items[4].value;
			var comments = items[5].value;
			
			
			var query = "empID="+empID+"&leaveID="+leaveID+"&leaveStartDate="+leaveStartDate+"&leaveEndDate="+leaveEndDate+"&approvalStatus="+approvalStatus+"&comments="+comments
			
			xmlHttp.open("POST","employeeLeave.htm",true);
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
			
			var empID = items[0].value;
			var taskID = items[1].value;
			var taskName  = items[2].value
			var taskStatus  = items[3].value 
			var employeeComment = items[4].value;
			var managerComment = items[5].value;
			
			var query = "empID="+empID+"&taskID="+taskID+"&taskName="+taskName+"&taskStatus="+taskStatus+"&employeeComment="+employeeComment+"&managerComment="+managerComment
			
			xmlHttp.open("POST","employeeTask.htm",true);
			xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xmlHttp.send(query);
            return false;
		}
		
		
		function deleteTask(i){
			if (xmlHttp === null)
            {
                alert("Your browser does not support AJAX!");
                return;
            }
			
			var count = "task"+i;
			var items = document.getElementsByClassName(count);
			
			var empID = items[0].value;
			var taskID = items[1].value;
			
			var query = "empID="+empID+"&taskID="+taskID

			
			xmlHttp.open("POST","deleteEmployeeTask.htm",true);
			xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xmlHttp.send(query);
            return false;
		}
		
		function checkeff(){
			var x = document.getElementById("efficientAreas").value;
		    var txt = x.replace(/</g,"");
		    document.getElementById("efficientAreas").value = txt;
		}
		
		function checkimp(){
			var x = document.getElementById("improvementAreas").value;
		    var txt = x.replace(/</g,"");
		    document.getElementById("improvementAreas").value = txt;
		}
</script>
	

</body>
</html>