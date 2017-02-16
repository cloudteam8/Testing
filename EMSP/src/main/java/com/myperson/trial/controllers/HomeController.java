package com.myperson.trial.controllers;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myperson.trial.pojo.Employee;
import com.myperson.trial.pojo.Manager;
import com.myperson.trial.pojo.Person;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	//private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(@ModelAttribute("person") Person person,HttpServletRequest request) {
		return "/index.jsp";
	}
	
	@RequestMapping(value = "/createUser.htm", method = RequestMethod.GET)
	public String adminCreateUserPage(@ModelAttribute("employee") Employee employee) {
		return "registerUser";
	}
	
	@RequestMapping(value = "/registerManager.htm", method = RequestMethod.GET)
	public String adminCreateManagePage(@ModelAttribute("manager") Manager manager) {
		return "registerManager";
	}
}