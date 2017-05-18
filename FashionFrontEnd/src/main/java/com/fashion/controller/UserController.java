package com.fashion.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fashion.dao.UserDAO;
import com.fashion.domain.User;

@Controller
public class UserController {
	
	private static Logger log=LoggerFactory.getLogger(UserController.class);

	@Autowired UserDAO userDAO;
	
	@Autowired User user;
	
	//get the user id and password from login page
	//send these values to userDAO.validate
	
	@RequestMapping("validate")
	public ModelAndView login(@RequestParam("userName") String id,@RequestParam("password") String password)
	{
		log.debug("starting of the method login");
		
		log.info("you are login with the id " +id);
		ModelAndView mv =new ModelAndView("/Home");
		if(userDAO.validate(id, password)==true)
		{
			log.debug("valid credentials");
			user=userDAO.get(id);
			
			mv.addObject("message","wellcome" +user.getName());
			
			if(user.getRole().equals("admin"))
			{
				log.debug("you are admin");
				mv.addObject("isAdmin", "true");
			}
			else
			{
				log.debug("you are customer");
				mv.addObject("isAdmin", "false");
			}
			
		}
	    
		else
		{
			log.debug("invalid user");
			mv.addObject("message", "Invalid credentials..please try again");
		}
		log.debug("ending of method login");
		return mv;
	}
	
	
}
