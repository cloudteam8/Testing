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
//import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndView;

import com.myperson.trial.doa.EmployeeDAO;
import com.myperson.trial.doa.FeedbackDAO;
import com.myperson.trial.doa.ManagerDAO;
import com.myperson.trial.doa.PersonDAO;
import com.myperson.trial.doa.RequestDAO;
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
@RequestMapping("/loginuser.htm")
public class LoginController {
	
	@RequestMapping(method=RequestMethod.POST)
	protected ModelAndView doSubmitAction(@ModelAttribute("person") Person person, @ModelAttribute("leaves") Leaves leaves, 
			@ModelAttribute("manager") Manager manager, @ModelAttribute("employee") Employee employee,
			@ModelAttribute("tasks") Tasks tasks, @ModelAttribute("workRequests") WorkRequests workRequests, 
			@ModelAttribute("perfFeedback") PerformanceFeedback perfFeedback, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		PersonDAO personDAO = new PersonDAO();
		RequestDAO requestDAO= new RequestDAO();
		List empList = new ArrayList();
		List leaveList = new ArrayList();
		List taskList = new ArrayList();
		List reqList = new ArrayList();
		HttpSession session =request.getSession();
		String userRoleType = "";
		String returnJSPValue="login";
		
		if(session.getAttribute("person")!=null){
			person = (Person)session.getAttribute("person");
		}else{
//			person = personDAO.getUserRole(person.getEmailAddress(),person.getPassword());
			person = personDAO.getUserRole(request.getParameter("username"),request.getParameter("password"));
			if(person!=null)
				session.setAttribute("person", person);
		}
		
		if(person!=null){
			userRoleType = person.getUserRole();
			if(userRoleType!=null){
				if(userRoleType.equalsIgnoreCase("admin")){
					reqList=requestDAO.getRequests();
					if(reqList.size()>0)
						mv.addObject("reqList", reqList);
					returnJSPValue = "adminHome";
				}
					
				else if(userRoleType.equalsIgnoreCase("employee")){
					person = (Person)session.getAttribute("person");
					EmployeeDAO employeeDAO = new EmployeeDAO();
					employee = employeeDAO.getEmployee(person.getEmpID());
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
						perfFeedback = feedbackDAO.checkperfGiven(person.getEmpID());
						if(perfFeedback!=null){
							showValue=2;
						}
						mv.addObject("leaveList", leaveList);
						mv.addObject("taskList", taskList);
						mv.addObject("showValue", showValue);
						returnJSPValue = "employeeHome";
					}else{
						returnJSPValue="index";
						mv.addObject("message", "Issue with the data in backend. Please contact Admin");
					}
				}
				
				else if(userRoleType.equalsIgnoreCase("manager")){
					person = (Person)session.getAttribute("person");
					ManagerDAO managerDAO = new ManagerDAO();
					manager = managerDAO.searchManager(person.getEmpID());
					if(manager!=null){
						Iterator empIterator = manager.getEmployees().iterator();
						
						
						while(empIterator.hasNext()){
							Employee emp = (Employee)empIterator.next();
							empList.add(emp);
						}
						
						Iterator wrkIterator = manager.getWorkRequests().iterator();
						
						
						while(wrkIterator.hasNext()){
							WorkRequests wrk = (WorkRequests)wrkIterator.next();
							if(wrk.getRequestStatus().equalsIgnoreCase("Open")||wrk.getRequestStatus().equalsIgnoreCase("In Progress")){
								reqList.add(wrk);
							}
						}
						
						mv.addObject("reqList", reqList);
						mv.addObject("employeeList", empList);
						returnJSPValue = "managerHome";
					}else{
						returnJSPValue="index";
						mv.addObject("message", "Issue with the data in backend. Please contact Admin");
					}
				}
				else{
					returnJSPValue="index";
					mv.addObject("message", "User Role not created correctly. Please contact Admin");
				}
			}else{
				returnJSPValue="index";
				mv.addObject("message", "User does not exist. Please contact Admin");
			}
		}
		else{
			returnJSPValue="index";
			mv.addObject("message", "Credentials provided were incorrect. Kindly try again");
		}
		mv.setViewName(returnJSPValue);
		return mv;
	}
}