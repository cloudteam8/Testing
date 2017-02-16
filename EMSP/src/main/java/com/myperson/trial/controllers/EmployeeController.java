/**
 * 
 */
package com.myperson.trial.controllers;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.myperson.trial.doa.EmployeeDAO;
import com.myperson.trial.doa.FeedbackDAO;
import com.myperson.trial.doa.LeavesDAO;
import com.myperson.trial.doa.PersonDAO;
import com.myperson.trial.doa.TasksDAO;
import com.myperson.trial.pojo.Employee;
import com.myperson.trial.pojo.Leaves;
import com.myperson.trial.pojo.PerformanceFeedback;
import com.myperson.trial.pojo.Person;
import com.myperson.trial.pojo.Tasks;
import org.json.JSONObject;

/**s
 * @author Christopher Dsouza
 *
 */

@Controller
public class EmployeeController {
	
	@RequestMapping(value="/updateEmployeeDetails.htm", method=RequestMethod.POST)
	protected void updateEmployeeDetails(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("person") Person person) throws Exception {
		ModelAndView mv  = new ModelAndView();
		PersonDAO personDAO = new PersonDAO();
		int empID = Integer.parseInt((String)request.getParameter("empID"));
		String fname = (String)request.getParameter("fname");
		String lname = (String)request.getParameter("lname");
		String pwd = (String)request.getParameter("password");
		long phn = Long.parseLong((String)request.getParameter("phn"));
		String street =  (String)request.getParameter("street");
		String city = (String)request.getParameter("city");
		String state = (String)request.getParameter("state");
		int zip = Integer.parseInt((String)request.getParameter("zip"));
		
		int result = personDAO.updateUser(empID, fname, lname, pwd, phn, street, city, state, zip);
		if(result>0){
			person = personDAO.getUser(empID);
			JSONObject personJson = new JSONObject();
			personJson.put("person",person);
			PrintWriter out = response.getWriter();
			out.println(personJson);
			mv.addObject("message", "Update Successful");
			}
		else
			mv.addObject("message", "Update Failed. Try again");
	}
	
	@RequestMapping(value="/leaves.htm", method=RequestMethod.POST)
	protected ModelAndView leavesRequested(@ModelAttribute(value="leaves") Leaves leaves, HttpServletRequest request, @ModelAttribute(value="tasks") Tasks tasks){
		ModelAndView mv = new ModelAndView();
		HttpSession session =request.getSession();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		LeavesDAO leavesDAO = new LeavesDAO();
		Person person = (Person)session.getAttribute("person");
		int employeeID = person.getEmpID();
		Employee emp = employeeDAO.getEmployee(employeeID);
		leaves = leavesDAO.addLeaves(leaves.getLeaveStartDate(), leaves.getLeaveEndDate(), emp);
		if(leaves!=null){
			mv.addObject("message", "Task Created successfully");
		}else{
			mv.addObject("message", "Task Creation failed");
		}
		mv = this.navigateToPage(request);
		return mv;
	}
	
	@RequestMapping(value="/tasks.htm", method=RequestMethod.POST)
	protected ModelAndView tasksCreated(@ModelAttribute(value="tasks") Tasks tasks, HttpServletRequest request, @ModelAttribute(value="leaves") Leaves leaves){
		ModelAndView mv = new ModelAndView();
		HttpSession session =request.getSession();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		TasksDAO tasksDAO = new TasksDAO();
		Person person = (Person)session.getAttribute("person");
		int employeeID = person.getEmpID();
		Employee emp = employeeDAO.getEmployee(employeeID);
		tasks = tasksDAO.createTasks(tasks.getTaskName(), tasks.getCurrentStatus(), tasks.getEmployeeComments(), "", emp);
		if(tasks!=null){
			mv.addObject("message", "Task Created successfully");
		}else{
			mv.addObject("message", "Task Creation failed");
		}
		mv = this.navigateToPage(request);
		return mv;
	}
	
	
	
	@RequestMapping(value="/updateEmployeeTask.htm", method=RequestMethod.POST)
	protected ModelAndView taskUpdate(@ModelAttribute(value="tasks") Tasks tasks, HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		TasksDAO tasksDAO = new TasksDAO();
		int updateTask = 0; 
		int taskID = Integer.parseInt((String)request.getParameter("taskID"));
		String employeeComment = (String)request.getParameter("employeeComment");
		String currentStatus = (String)request.getParameter("taskStatus");
		updateTask = tasksDAO.updateEmployeeTasks(taskID, currentStatus, employeeComment);
		if(updateTask>0)
		{
			mv.addObject("message", "Task Created successfully");
		}else{
			mv.addObject("message", "Task Creation failed");
		}
		
		mv =  this.navigateToPage(request);
//		HttpSession session =request.getSession();
//		Person person = (Person)session.getAttribute("person");
//		EmployeeDAO employeeDAO = new EmployeeDAO();
//		List leaveList = new ArrayList();
//		List taskList = new ArrayList();
//		Employee employee = employeeDAO.getEmployee(person.getEmpID());
//		if(employee!=null){
//			int showValue=0;
//			Iterator leaveIterator = employee.getLeav().iterator();
//			
//			while(leaveIterator.hasNext()){
//				Leaves lea = (Leaves)leaveIterator.next();
//				leaveList.add(lea);
//			}
//			
//			Iterator taskIterator = employee.getTasks().iterator();
//			
//			while(taskIterator.hasNext()){
//				Tasks tas = (Tasks)taskIterator.next();
//				taskList.add(tas);
//			}
//			
//			FeedbackDAO feedbackDAO = new FeedbackDAO();
//			PerformanceFeedback perfFeedback = feedbackDAO.checkperfGiven(person.getEmpID());
//			if(perfFeedback!=null){
//				showValue=2;
//			}
//			mv.addObject("leaveList", leaveList);
//			mv.addObject("taskList", taskList);
//			mv.addObject("showValue", showValue);
//			mv.setViewName("employeeHome");
//		}else{
//			session.invalidate();
//			mv.setViewName("index");
//			mv.addObject("message", "Issue with the data in backend. Please contact Admin");
//		}
//		
//		mv.setViewName("employeeHome");
		return mv;
	}
	
	
	@RequestMapping(value = "/feedback.pdf", method=RequestMethod.POST)
	public ModelAndView initializeForm(@ModelAttribute("perfFeedback")PerformanceFeedback perfFeedback, HttpServletRequest request){
		
		HttpSession session =request.getSession();
		FeedbackDAO feedbackDAO = new FeedbackDAO();
		Person person = (Person)session.getAttribute("person");
		perfFeedback = feedbackDAO.getFeedback(person.getEmpID());
		request.setAttribute("empID", person.getEmpID());
		request.setAttribute("firstName", person.getFirstName());
		request.setAttribute("lastName", person.getLastName());
		request.setAttribute("emailAddress", person.getEmailAddress());
		request.setAttribute("designation", person.getDesignation());
		request.setAttribute("managerFirstName", perfFeedback.getManager().getFirstName());
		request.setAttribute("managerLastName", perfFeedback.getManager().getLastName());
		request.setAttribute("managerID", perfFeedback.getManager().getManagerID());
		request.setAttribute("managerEmailAddress", perfFeedback.getManager().getEmailAddress());
		request.setAttribute("managerDesignation", perfFeedback.getManager().getDesignation());
		request.setAttribute("valueCreator", perfFeedback.getValueCreator());
		request.setAttribute("peopleDeveloper", perfFeedback.getPeopleDeveloper());
		request.setAttribute("businessOperator", perfFeedback.getBusinessOperator());
		request.setAttribute("technicalSkills", perfFeedback.getTechnicalSkills());
		request.setAttribute("taskCompletionSkills", perfFeedback.getTaskCompletionSkills());
		request.setAttribute("uninformedLease", perfFeedback.getUninformedLeavesTaken());
		request.setAttribute("communicationSkills", perfFeedback.getCommunicationSkills());
		request.setAttribute("efficientAreas", perfFeedback.getEfficientAreas());
		request.setAttribute("improvementAreas", perfFeedback.getImprovementAreas());
		

		View view = new FeedbackPDFView();
		ModelAndView mv = new ModelAndView();	
		mv.setView(view);
		return mv;
	}
	
	
	public ModelAndView navigateToPage(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		HttpSession session =request.getSession();
		Person person = (Person)session.getAttribute("person");
		EmployeeDAO employeeDAO = new EmployeeDAO();
		List leaveList = new ArrayList();
		List taskList = new ArrayList();
		Employee employee = employeeDAO.getEmployee(person.getEmpID());
		if(employee!=null){
			int showValue=0;
			Iterator leaveIterator = employee.getLeav().iterator();
			
			while(leaveIterator.hasNext()){
				Leaves lea = (Leaves)leaveIterator.next();
				leaveList.add(lea);
			}
			
			Iterator taskIterator = employee.getTasks().iterator();
			
			while(taskIterator.hasNext()){
				Tasks tas = (Tasks)taskIterator.next();
				taskList.add(tas);
			}
			
			FeedbackDAO feedbackDAO = new FeedbackDAO();
			PerformanceFeedback perfFeedback = feedbackDAO.checkperfGiven(person.getEmpID());
			if(perfFeedback!=null){
				showValue=2;
			}
			mv.addObject("leaveList", leaveList);
			mv.addObject("taskList", taskList);
			mv.addObject("showValue", showValue);
			mv.setViewName("employeeHome");
		}else{
			session.invalidate();
			mv.setViewName("/index.jsp");
			mv.addObject("message", "Issue with the data in backend. Please contact Admin");
		}
		
		mv.setViewName("employeeHome");
		
		return mv;
	}
}