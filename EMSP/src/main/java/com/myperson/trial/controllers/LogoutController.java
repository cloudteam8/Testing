/**
 * 
 */
package com.myperson.trial.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myperson.trial.pojo.Person;

/**
 * @author Christopher Dsouza
 *
 */

@Controller
public class LogoutController {
	@RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
	public String doLogout(HttpServletRequest request, @ModelAttribute("person") Person person){
		HttpSession session =request.getSession();
		session.invalidate();
		//return "/index.jsp";
		return "index";
	}
}