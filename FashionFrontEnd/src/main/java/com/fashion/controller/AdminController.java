package com.fashion.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
	
	private static Logger log=LoggerFactory.getLogger("AdminController");
	
	@RequestMapping("/manageCategories")
	public ModelAndView manageCategories()
	{
		log.debug("starting of the method manage categories");
		ModelAndView mv= new ModelAndView("Home");
		mv.addObject("isAdminClickedCategories", "true");
		mv.addObject("isAdmin","true");
		log.debug("end of the method manageCategories");
		return mv;
	}
	
	@RequestMapping("/manageSupplier")
	public ModelAndView manageSuppliers()
	{
		log.debug("starting of the method manageSuppliers");
		ModelAndView mv= new ModelAndView("Home");
		mv.addObject("isAdminClickedSuppliers", "true");
		mv.addObject("isAdmin","true");
		log.debug("ending of the method manageSuppliers");
		return mv;
	}
	@RequestMapping("/manageProducts")
	public ModelAndView manageProducts()
	
	{
		log.debug("Starting of the method manageProducts");
		ModelAndView mv= new ModelAndView("Home");
		mv.addObject("isAdmin", "true");
		mv.addObject("isAdminClickedProducts", "true");
		log.debug("Ending of the method manageProducts");
		return mv;
		
	}

}
