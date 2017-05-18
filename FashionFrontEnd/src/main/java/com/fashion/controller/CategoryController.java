package com.fashion.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fashion.dao.CategoryDAO;
import com.fashion.dao.ProductDAO;
import com.fashion.domain.Category;
import com.fashion.domain.Product;

@Controller
public class CategoryController {
	
 private static Logger log =LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired CategoryDAO categoryDAO;
	
	@Autowired Category category;
	
	@Autowired ProductDAO productDAO;
	
	@Autowired Product product;
	
	@Autowired HttpSession session;
	
	@RequestMapping("/manage_category_add")
	public ModelAndView createCategory(@RequestParam("id") String id,@RequestParam("name") String name,@RequestParam("description") String desc)
	{
		log.debug("Starting of the method managecategories");
		category.setId(id);
		category.setName(name);
		category.setDescription(desc);
		
		ModelAndView mv= new ModelAndView("Home");
		mv.addObject("isAdminClickedCategories", "true");
		mv.addObject("isAdmin", "true");
		
		if(categoryDAO.getcategorybyid(id)!=null)
		{//category already exist
		mv.addObject("message", "Category already exist with this id "+id);
		return mv;
			
		}
		else
		{
			categoryDAO.save(category);
			mv.addObject("message", "Category created successfully");
			
		}
		
		session.setAttribute("categoryList", categoryDAO.list());
		session.setAttribute("category", category);
		log.debug("ending of the managecategories");
		return mv;
	}

	@RequestMapping("/manage_category_delete/{id}")
	public ModelAndView deleteCategory(@PathVariable("id") String id)
	{
		log.debug("Starting of the method deleteCategories");
		log.debug("you are going to delete"+id);
		ModelAndView mv=new ModelAndView("redirect:/manageCategories");
		
		//check whether products are there in category or not
		
		int size=productDAO.getAllProductsbyCategoryId(id).size();
		
		if(size!=0)
		{
			log.debug("few products are there under the category..you cant delete");
			mv.addObject("message",size+"products are under the category "+id+" .you cant delete " );
			
			session.setAttribute("message",size+ "products are there under category "+id+" you cant delete it");
		   return mv;
		}
		
		
		
		if(categoryDAO.delete(id))
		{
			mv.addObject("message", "successfully delete the category");
		}
		else
		{
			mv.addObject("message", "not able to delete the category");
		}
		
		session.setAttribute("categoryList", categoryDAO.list());
		session.setAttribute("category", category);
		log.debug("ending of the method deletecategory");
		return mv;
	}
	
	@RequestMapping("/manage_category_edit/{id}")
	public ModelAndView editCategory(@PathVariable("id") String id)
	{
		log.debug("Starting of the method editCategory");
		log.debug("Goging to edit category:"+id);
		category=categoryDAO.getcategorybyid(id);
		 
		ModelAndView mv=new ModelAndView("redirect:/manageCategories");
	    session.setAttribute("selectedCategory", category);
	    mv.addObject("selectedCategory",category );
	    log.debug("ending of the method editCategory");
	    
	   return mv;
	}
	
	@PostMapping("/manage_category_update")
	public ModelAndView updateCategory(@RequestParam("id") String id,@RequestParam("name") String name, @RequestParam("description") String desc)
	
	{
	log.debug("Starting of the method updateCategory");
	category.setId(id);
	category.setName(name);
	category.setDescription(desc);
	ModelAndView mv= new ModelAndView("redirect:/manageCategories");
	 
	if(categoryDAO.getcategorybyid(id)==null)
	{
		//category does not exist
		mv.addObject("message", "Category does now exist with the id"+id);
		return mv;
		
	}
	else
	{
		categoryDAO.update(category);
		mv.addObject("message", "Category update successfully");
	}
	
	session.setAttribute("categoryList", categoryDAO.list());
	session.setAttribute("category", category);
	log.debug("Ending of the method updateCategory");
	return mv;
	
	}
	
	
	
	
	
	
}
