package com.fashion.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fashion.dao.SupplierDAO;
import com.fashion.domain.Supplier;

@Controller
public class SupplierController {

	private static Logger log =LoggerFactory.getLogger(SupplierController.class);
	//create supplier
	//fetch all supplier
	//delete supplier
	//update supplier
	
	@Autowired SupplierDAO supplierDAO;
	
	@Autowired Supplier supplier;
	
	@Autowired HttpSession session;
	
	
	@GetMapping("/manage_supplier_add")
	public ModelAndView createSupplier(@RequestParam("id") String id, @RequestParam("name") String name, @RequestParam("address") String address)
	
	{
		log.debug("Starting of the method manageSupplier");
		supplier.setId(id);
		supplier.setName(name);
		supplier.setAddress(address);
		
		ModelAndView mv=new ModelAndView("redirect:/manageSupplier");
		
		mv.addObject("isAdminClickedSuppliers", "true");
		mv.addObject("isAdmin", "true");
		
		if(supplierDAO.getsupplierbyid(id)!=null)
		{
			//supplier already exist
			mv.addObject("message","Supplier already exist with the id"+id);
			return mv;
		}
		else
		{
			supplierDAO.save(supplier);
			mv.addObject("message", "Supplier created successfuly");
			
		}
		session.setAttribute("supplierList", supplierDAO.list());
		session.setAttribute("supplier", supplier);
		log.debug("end of the method manageSupplier");
		return mv;
	
		}
	@GetMapping("/manage_supplier_delete/{id}")
	public ModelAndView deleteSupplier(@PathVariable("id") String id)
	{
		log.debug("Starting of the method deleteSupplier");
		log.debug("you are going to delete"+id);
		ModelAndView mv= new ModelAndView("redirect:/manageSupplier");
		
		if(supplierDAO.delete(id))
		{
			mv.addObject("message", "successfully deleted the supplier");
			
		}
		else
		{
			mv.addObject("message", "not able to delete the supplier");
		}
		session.setAttribute("supplierList", supplierDAO.list());
		session.setAttribute("supplier", supplier);
		log.debug("Ending of the method deletesupplier");
		return mv;
	}
	
	
	@RequestMapping("/manage_supplier_edit/{id}")
	public ModelAndView editSupplier(@PathVariable("id") String id)
	{
		log.debug("Starting of the method editSupplier");
		log.debug("going to edit the supplier :"+id);
		supplier=supplierDAO.getsupplierbyid(id);
		
		ModelAndView mv= new ModelAndView("redirect:/manageSupplier");
		session.setAttribute("selectedSupplier", supplier);
		mv.addObject("selectedSupplier", supplier);
		log.debug("Ending of the method editSupplier");
		return mv;
		
	}
	
	@PostMapping("/manage_supplier_update")
	public ModelAndView updateSupplier(@RequestParam("id") String id,@RequestParam("name") String name, @RequestParam("address") String address)
	{
		log.debug("Starting of the method updateSupplier");
		supplier.setId(id);
		supplier.setName(name);
		supplier.setAddress(address);
		
		ModelAndView mv=new ModelAndView("redirect:/manageSupplier");
		
		if(supplierDAO.getsupplierbyid(id)==null)
		{
			//supplier already exist
			mv.addObject("message", "Supplier does now exist with id"+id);
			return mv;
		}
		else
		{
			supplierDAO.update(supplier);
			mv.addObject("message", "Supplier update successfully");
		}
		
		session.setAttribute("supplierList", supplierDAO.list());
		session.setAttribute("supplier", supplier);
		
		log.debug("Ending of the method updateSupplier");
		
		return mv;
		
		
		
		
		
		
		
	}
	
}
	