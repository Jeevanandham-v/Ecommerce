package com.fashion.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fashion.dao.CategoryDAO;
import com.fashion.dao.ProductDAO;
import com.fashion.dao.SupplierDAO;
import com.fashion.domain.Category;
import com.fashion.domain.Product;
import com.fashion.domain.Supplier;

@Controller
public class HomeController {
	
	//http://localhost:8080/ShoppingCart/
	
	@Autowired HttpSession session;
	
	@Autowired Category category;
	
	@Autowired CategoryDAO categoryDAO;
	
	
	@Autowired SupplierDAO supplierDAO;
	
	@Autowired Supplier supplier;
	
	@Autowired ProductDAO productDAO;
	
	@Autowired Product product;
	
	@RequestMapping("/")
	public  ModelAndView    goToHome(Model model)
	
	{
		ModelAndView mv= new ModelAndView("Home");
		//model.addAttribute("message", "Thank you for visiting Shopping Cart");
		mv.addObject("message", "Thank you for visiting Shopping Cart");
		List<Category> categoryList =categoryDAO.list();
		
		session.setAttribute("categoryList", categoryList);
		session.setAttribute("category", category);
		
		List<Supplier> supplierList =supplierDAO.list();
		session.setAttribute("supplierList", supplierList);
		session.setAttribute("supplier", supplier);
		
		List<Product> productList=productDAO.list();
		session.setAttribute("productList", productList);
		session.setAttribute("product", product);
		
		
		
		
		return mv;
		//return "Home";
	}
	
	
	@RequestMapping("/LoginPage")
	public String loginPage(Model model)
	{
		model.addAttribute("isUserClickedLogin", "true");
	
		return "Home";
	}
	
	@RequestMapping("/RegistrationPage")
	public String registrationPage(Model model)
	{
		model.addAttribute("isUserClickedRegister", "true");
		
		return "Home";
	}


}
