/**
 * 
 */
package com.myperson.trial.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
public class AdminController {
	
	@RequestMapping(value="/registeruser.htm", method=RequestMethod.POST)
	public ModelAndView createUser(@ModelAttribute("employee") Employee employee,@ModelAttribute("person") Person person,@ModelAttribute("workRequests") WorkRequests workRequests){
		ModelAndView mv = new ModelAndView();
		PersonDAO personDAO = new PersonDAO();
		ManagerDAO managerDAO = new ManagerDAO();
		RequestDAO requestDAO= new RequestDAO();
		List reqList = new ArrayList();
		Manager manager = new Manager();
		String managerEmail = employee.getManagerEmail();
		int mngrEmpID = personDAO.getManagerEmployeeID(managerEmail);
		if(mngrEmpID>0)
		{
			manager = managerDAO.searchManager(mngrEmpID);
			employee = personDAO.createUser(employee.getFirstName(), employee.getLastName(), employee.getEmpID(), employee.getEmailAddress(),employee.getPassword(),employee.getPhoneNumber(), employee.getDesignation(),employee.getUserRole(), employee.getStreetName(), employee.getCity(), employee.getState(), employee.getZipCode(),manager);
			if(employee!=null){
				reqList=requestDAO.getRequests();
				if(reqList.size()>0)
					mv.addObject("reqList", reqList);
				mv.addObject("message", "User successfully Created");
				mv.setViewName("adminHome");
				}
			else{
				mv.addObject("message", "User creation failed. Please try again");
				mv.setViewName("registerUser");
			}
		}
		else{
			mv.addObject("message", "User creation failed. Manager not found. Please try again");
			mv.setViewName("registerUser");
		}
		return mv;
	}
	
	
	
	@RequestMapping(value="/registermngr.htm", method=RequestMethod.POST)
	public ModelAndView createUser(@ModelAttribute("manager") Manager manager, @ModelAttribute("person") Person person,@ModelAttribute("workRequests") WorkRequests workRequests){
		ModelAndView mv = new ModelAndView();
		ManagerDAO managerDAO = new ManagerDAO();
		RequestDAO requestDAO= new RequestDAO();
		List reqList = new ArrayList();
		manager = managerDAO.createManagerUser(manager.getFirstName(), manager.getLastName(), manager.getEmpID(), manager.getEmailAddress(),manager.getPassword(),manager.getPhoneNumber(), manager.getDesignation(),manager.getUserRole(), manager.getStreetName(), manager.getCity(), manager.getState(), manager.getZipCode(),manager.getManagerID());
		if(manager!=null){
			reqList=requestDAO.getRequests();
			if(reqList.size()>0)
				mv.addObject("reqList", reqList);
				mv.addObject("message", "User for Manager successfully Created");
				mv.setViewName("adminHome");
		}else{
			mv.addObject("message", "User creation failed. Please try again");
			mv.setViewName("registerUser");
		}
		return mv;
		
	}
	
	
	@RequestMapping(value="/searchUser.htm", method=RequestMethod.POST)
	protected ModelAndView searchUser(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("person") Person person, @ModelAttribute("leaves") Leaves leaves, 
			@ModelAttribute("manager") Manager manager, @ModelAttribute("employee") Employee employee,
			@ModelAttribute("workRequests") WorkRequests workRequests) throws Exception {
		
		ModelAndView mv  = new ModelAndView();
		PersonDAO personDAO = new PersonDAO();
		RequestDAO requestDAO= new RequestDAO();
		List reqList = new ArrayList();
		person = personDAO.searchUser(person.getEmailAddress());
		if(person!=null){
			mv.addObject("person", person);
			mv.setViewName("searchResult");
		}else{
			reqList=requestDAO.getRequests();
			if(reqList.size()>0)
				mv.addObject("reqList", reqList);
			mv.addObject("message", "Email Address searched does not exist.Please try again");
			mv.setViewName("adminHome");
		}
		return mv;
	}
	
	@RequestMapping(value="/deleteUser.htm", method=RequestMethod.POST)
	protected ModelAndView deleteUserDetails(@ModelAttribute("person") Person person, @ModelAttribute("workRequests") WorkRequests workRequests, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		PersonDAO personDAO = new PersonDAO();
		int empID = Integer.parseInt((String)request.getParameter("empID"));
		RequestDAO requestDAO= new RequestDAO();
		List reqList = new ArrayList();
//		String fname = (String)request.getParameter("fname");
//		String lname = (String)request.getParameter("lname");
//		String email = (String)request.getParameter("email");
//		long phn = Long.parseLong((String)request.getParameter("phn"));
//		String desig = (String)request.getParameter("desig");
//		String street =  (String)request.getParameter("street");
//		String city = (String)request.getParameter("city");
//		String state = (String)request.getParameter("state");
//		int zip = Integer.parseInt((String)request.getParameter("zip"));
		
		//person = personDAO.deleteUser(employee.getEmpID(), employee.getFirstName(), employee.getLastName(), employee.getEmailAddress(), employee.getPhoneNumber(), employee.getDesignation(), employee.getStreetName(), employee.getCity(), employee.getState(), employee.getZipCode());
		personDAO.deleteUser(empID);
		reqList=requestDAO.getRequests();
		if(reqList.size()>0)
			mv.addObject("reqList", reqList);
		mv.setViewName("searchResult");
		return mv;
	}
	
	@RequestMapping(value="/userRole", method=RequestMethod.POST)
	public ModelAndView manageUserRole(@ModelAttribute("person") Person person,@ModelAttribute("workRequests") WorkRequests workRequests, HttpServletRequest request){
		int result = 0;
		ModelAndView mv= new ModelAndView();
		PersonDAO personDAO =new PersonDAO();
		RequestDAO requestDAO= new RequestDAO();
		List reqList = new ArrayList();
		result = personDAO.updateUserRole(person.getEmpID(), person.getUserRole());
		if(result>0){
			mv.addObject("message", "User Role successfully updated");
		}else{
			mv.addObject("message", "User Role updation failed. Please try again");
		}
		reqList=requestDAO.getRequests();
		if(reqList.size()>0)
			mv.addObject("reqList", reqList);
		mv.setViewName("adminHome");
		return mv;
	}
	
	
	@RequestMapping(value="/adminUpdateRequests.htm", method=RequestMethod.POST)
	protected ModelAndView mgrUpdateRequests(@ModelAttribute("manager") Manager manager, @ModelAttribute("workRequests") WorkRequests workRequests, 
			@ModelAttribute("person") Person person, HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		RequestDAO requestDAO =  new RequestDAO();
		List reqList = new ArrayList();
		int result=0;
		
		int workRequestID = Integer.parseInt((String)request.getParameter("workRequestID"));
		String adminComments = (String)request.getParameter("adminComments");
		String status = (String)request.getParameter("requestStatus");
		result = requestDAO.adminUpdateTasks(workRequestID, adminComments, status);
		if(result>0){
			mv.addObject("message", "Task successfully updated");
		}else{
			mv.addObject("message", "Task updation failed");
		}
		
		reqList=requestDAO.getRequests();
		if(reqList.size()>0)
			mv.addObject("reqList", reqList);
		
		mv.setViewName("adminHome");
		return mv;
	}
}