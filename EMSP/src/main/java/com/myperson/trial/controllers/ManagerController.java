/**
 * 
 */
package com.myperson.trial.controllers;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myperson.trial.doa.EmployeeDAO;
import com.myperson.trial.doa.FeedbackDAO;
import com.myperson.trial.doa.LeavesDAO;
import com.myperson.trial.doa.ManagerDAO;
import com.myperson.trial.doa.RequestDAO;
import com.myperson.trial.doa.TasksDAO;
import com.myperson.trial.pojo.Employee;
import com.myperson.trial.pojo.Leaves;
import com.myperson.trial.pojo.Manager;
import com.myperson.trial.pojo.PerformanceFeedback;
import com.myperson.trial.pojo.Person;
import com.myperson.trial.pojo.Tasks;
import com.myperson.trial.pojo.WorkRequests;

/**
 * @author Christopher Dsouza
 *
 */
@Controller
public class ManagerController {
	
	@RequestMapping(value="/employeede.htm", method=RequestMethod.POST)
	protected ModelAndView doSubmitAction(@ModelAttribute("leaves") Leaves leaves, @ModelAttribute("employee") Employee employee,
			@ModelAttribute("tasks") Tasks tasks, @ModelAttribute("perfFeedback") PerformanceFeedback perfFeedback, HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		FeedbackDAO feedbackDAO = new FeedbackDAO();
		List leaveList = new ArrayList();
		List taskList = new ArrayList();
		int empID = employee.getEmpID();
		int showValue=2;
		
		perfFeedback = feedbackDAO.checkperfGiven(empID);
		if(perfFeedback!=null){
			showValue=0;
		}
		
		employee = employeeDAO.getEmployee(empID);
			
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
		
		mv.addObject("leaveList", leaveList);
		mv.addObject("employee", employee);
		mv.addObject("taskList", taskList);
		mv.addObject("showValue", showValue);
		mv.setViewName("employeeDetails");
		
		return mv;
	}
	
	
	@RequestMapping(value="/employeeLeave.htm", method=RequestMethod.POST)
	protected ModelAndView leavesUpdate(@ModelAttribute("leaves") Leaves leaves, @ModelAttribute("employee") Employee employee,
			@ModelAttribute("tasks") Tasks tasks, @ModelAttribute("perfFeedback") PerformanceFeedback perfFeedback, HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		LeavesDAO leavesDAO = new LeavesDAO();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		FeedbackDAO feedbackDAO = new FeedbackDAO();
		List leaveList = new ArrayList();
		List taskList = new ArrayList();
		int showValue = 2;
		int empID = employee.getEmpID();
		int leaveID = Integer.parseInt((String)request.getParameter("leaveID"));
		String approvalString = (String)request.getParameter("approvalStatus");
		char approvalStatus = approvalString.charAt(0);
		String comments = request.getParameter("comments");
		leavesDAO.updateLeaveStatus(leaveID,approvalStatus, comments);
		
		perfFeedback = feedbackDAO.checkperfGiven(empID);
		if(perfFeedback!=null){
			showValue=0;
		}
		
		employee = employeeDAO.getEmployee(empID);
			
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
		
		mv.addObject("leaveList", leaveList);
		mv.addObject("employee", employee);
		mv.addObject("taskList", taskList);
		mv.addObject("showValue", showValue);
		mv.setViewName("employeeDetails");
		
		return mv;
	}
	
	
	
	
	@RequestMapping(value="/employeeTask.htm", method=RequestMethod.POST)
	protected ModelAndView taskUpdate(@ModelAttribute("leaves") Leaves leaves, @ModelAttribute("employee") Employee employee,
			@ModelAttribute("tasks") Tasks tasks, @ModelAttribute("perfFeedback") PerformanceFeedback perfFeedback, HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		TasksDAO tasksDAO = new TasksDAO();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		FeedbackDAO feedbackDAO = new FeedbackDAO();
		List leaveList = new ArrayList();
		List taskList = new ArrayList();
		int showValue = 2;
		int empID = employee.getEmpID();
		int taskID = Integer.parseInt((String)request.getParameter("taskID"));
		String managerComment = (String)request.getParameter("managerComment");
		tasksDAO.updateTasks(taskID, managerComment);
		
		perfFeedback = feedbackDAO.checkperfGiven(empID);
		if(perfFeedback!=null){
			showValue=0;
		}
		
		employee = employeeDAO.getEmployee(empID);
			
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
		
		mv.addObject("leaveList", leaveList);
		mv.addObject("employee", employee);
		mv.addObject("taskList", taskList);
		mv.addObject("showValue", showValue);
		mv.setViewName("employeeDetails");
		return mv;
	}
	
	
	@RequestMapping(value="/tasksManager.htm", method=RequestMethod.POST)
	protected ModelAndView tasksCreated(@ModelAttribute(value="tasks") Tasks tasks,@ModelAttribute(value="person") Person person, 
			@ModelAttribute("manager") Manager manager, @ModelAttribute("employee") Employee employee, @ModelAttribute("workRequests") WorkRequests workRequests, 
			HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		TasksDAO tasksDAO = new TasksDAO();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		List empList = new ArrayList();
		List reqList = new ArrayList();
		HttpSession session =request.getSession();
		int employeeID = Integer.parseInt((String)request.getParameter("empID"));
		Employee emp = employeeDAO.getEmployee(employeeID);
		tasksDAO.createTasks(tasks.getTaskName(), tasks.getCurrentStatus(), "", tasks.getSupervisorComment(), emp);
		
		person = (Person)session.getAttribute("person");
		ManagerDAO managerDAO = new ManagerDAO();
		manager = managerDAO.searchManager(person.getEmpID());
		
		Iterator empIterator = manager.getEmployees().iterator();
		
		while(empIterator.hasNext()){
			emp = (Employee)empIterator.next();
			empList.add(emp);
		}
		
		Iterator reqIterator = manager.getWorkRequests().iterator();
		
		while(reqIterator.hasNext()){
			WorkRequests req = (WorkRequests)reqIterator.next();
			if(req.getRequestStatus().equalsIgnoreCase("Open")||req.getRequestStatus().equalsIgnoreCase("In Progress")){
				reqList.add(req);
			}
		}
		
		mv.addObject("employeeList", empList);
		mv.addObject("reqList", reqList);
		mv.setViewName("managerHome");
		return mv;
	}
	
	
	@RequestMapping(value="/deleteEmployeeTask.htm", method=RequestMethod.POST)
	protected ModelAndView tasksDeleted(@ModelAttribute(value="tasks") Tasks tasks,@ModelAttribute(value="person") Person person, 
			@ModelAttribute("manager") Manager manager, @ModelAttribute("employee") Employee employee,@ModelAttribute("workRequests") WorkRequests workRequests, 
			HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		TasksDAO tasksDAO = new TasksDAO();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		ManagerDAO managerDAO = new ManagerDAO();
		List empList = new ArrayList();
		List reqList = new ArrayList();
		HttpSession session =request.getSession();
		int employeeID = Integer.parseInt((String)request.getParameter("empID"));
		Employee emp = employeeDAO.getEmployee(employeeID);
		tasksDAO.deleteTasks(tasks.getTaskID());
		
		person = (Person)session.getAttribute("person");
		manager = managerDAO.searchManager(person.getEmpID());
		
		Iterator empIterator = manager.getEmployees().iterator();
		
		while(empIterator.hasNext()){
			emp = (Employee)empIterator.next();
			empList.add(emp);
		}
		
		Iterator reqIterator = manager.getWorkRequests().iterator();
		
		while(reqIterator.hasNext()){
			WorkRequests req = (WorkRequests)reqIterator.next();
			if(req.getRequestStatus().equalsIgnoreCase("Open")||req.getRequestStatus().equalsIgnoreCase("In Progress")){
				reqList.add(req);
			}
		}
		
		mv.addObject("employeeList", empList);
		mv.addObject("reqList", reqList);
		mv.setViewName("managerHome");
		return mv;
	}
	
	
	@RequestMapping(value="/createEmployeeRequests.htm", method=RequestMethod.POST)
	protected ModelAndView createRequests(@ModelAttribute(value="tasks") Tasks tasks,@ModelAttribute(value="person") Person person, 
			@ModelAttribute("manager") Manager manager, @ModelAttribute("employee") Employee employee, @ModelAttribute("workRequests") WorkRequests workRequests, 
			HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		RequestDAO requestDAO =  new RequestDAO();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		ManagerDAO managerDAO = new ManagerDAO();
		List empList = new ArrayList();
		List reqList = new ArrayList();
		HttpSession session =request.getSession();
		int employeeID = workRequests.getEmployeeID();
		employee = employeeDAO.getEmployee(employeeID);
		
		person = (Person)session.getAttribute("person");
		manager = managerDAO.searchManager(person.getEmpID());
		workRequests = requestDAO.createWorkRequests(workRequests.getDesignation(), workRequests.getEmailAddress(), workRequests.getFirstName(), workRequests.getLastName(), workRequests.getUserRole(), workRequests.getManagerComments(), workRequests.getRequestStatus(), workRequests.getEmployeeID(), manager);
		if(workRequests!=null){
			mv.addObject("message", "Request Successfully Created");
		}else{
			mv.addObject("message", "Request Creation failed");
		}
		Iterator empIterator = manager.getEmployees().iterator();
		
		while(empIterator.hasNext()){
			employee = (Employee)empIterator.next();
			empList.add(employee);
		}
		
		Iterator reqIterator = manager.getWorkRequests().iterator();
		
		while(reqIterator.hasNext()){
			WorkRequests req = (WorkRequests)reqIterator.next();
			if(req.getRequestStatus().equalsIgnoreCase("Open")||req.getRequestStatus().equalsIgnoreCase("In Progress")){
				reqList.add(req);
			}
		}
		
		mv.addObject("employeeList", empList);
		mv.addObject("reqList", reqList);
		mv.setViewName("managerHome");
		return mv;
	}
	
	
	
	@RequestMapping(value="/mgrUpdateRequests.htm", method=RequestMethod.POST)
	protected ModelAndView mgrUpdateRequests(@ModelAttribute(value="tasks") Tasks tasks,@ModelAttribute(value="person") Person person, 
			@ModelAttribute("manager") Manager manager, @ModelAttribute("employee") Employee employee, @ModelAttribute("workRequests") WorkRequests workRequests, 
			HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		RequestDAO requestDAO =  new RequestDAO();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		ManagerDAO managerDAO = new ManagerDAO();
		List empList = new ArrayList();
		List reqList = new ArrayList();
		HttpSession session =request.getSession();
		int employeeID = Integer.parseInt((String)request.getParameter("empID"));
		employee = employeeDAO.getEmployee(employeeID);
		
		person = (Person)session.getAttribute("person");
		manager = managerDAO.searchManager(person.getEmpID());
		
		int workRequestID = Integer.parseInt((String)request.getParameter("workRequestID"));
		String managerComments = (String)request.getParameter("mgrcomments");
		String status = (String)request.getParameter("requestStatus");
		int result = 0;
		result = requestDAO.mgrUpdateTasks(workRequestID, managerComments, status);
		if(result>0){
			mv.addObject("message", "Update Successfully");
		}else{
			mv.addObject("message", "Update Failed");
		}
		Iterator empIterator = manager.getEmployees().iterator();
		
		while(empIterator.hasNext()){
			employee = (Employee)empIterator.next();
			empList.add(employee);
		}
		
		reqList=requestDAO.getRequests();
		if(reqList.size()>0)
			mv.addObject("reqList", reqList);
		
		mv.addObject("employeeList", empList);
		mv.setViewName("managerHome");
		return mv;
	}
	
	
	
	
	
	
	
	@RequestMapping(value="/giveFeedback.htm", method=RequestMethod.POST)
	protected ModelAndView giveFeedback(@ModelAttribute(value="perfFeedback")PerformanceFeedback perfFeedback, @ModelAttribute(value="person") Person person, 
			@ModelAttribute("manager") Manager manager, @ModelAttribute("employee")Employee employee, @ModelAttribute("workRequests")WorkRequests workRequests, HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		ManagerDAO managerDAO = new ManagerDAO();
		RequestDAO requestDAO =  new RequestDAO();
		FeedbackDAO feedbackDAO = new FeedbackDAO();
		List empList = new ArrayList();
		List reqList = new ArrayList();
		HttpSession session =request.getSession();
		
		int employeeID = Integer.parseInt((String)request.getParameter("empID"));
		employee = employeeDAO.getEmployee(employeeID);
		
		person = (Person)session.getAttribute("person");
		manager = managerDAO.searchManager(person.getEmpID());
		
		perfFeedback = feedbackDAO.giveFeedback(perfFeedback.getBusinessOperator(), perfFeedback.getPeopleDeveloper(), perfFeedback.getValueCreator(), perfFeedback.getCommunicationSkills(), perfFeedback.getTechnicalSkills(), perfFeedback.getTaskCompletionSkills(), perfFeedback.getUninformedLeavesTaken(), perfFeedback.getImprovementAreas(), perfFeedback.getEfficientAreas(), employee, manager);
		if(perfFeedback!=null){
			mv.addObject("message", "Feedback Successfully Given");
		}else{
			mv.addObject("message", "Try again. Feedback failed");
		}
		Iterator empIterator = manager.getEmployees().iterator();
		
		while(empIterator.hasNext()){
			employee = (Employee)empIterator.next();
			empList.add(employee);
		}
		
		reqList=requestDAO.getRequests();
		if(reqList.size()>0)
			mv.addObject("reqList", reqList);
		
		mv.addObject("employeeList", empList);
		mv.setViewName("managerHome");
		return mv;
	}
}
