package com.fashion.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fashion.dao.CategoryDAO;
import com.fashion.dao.ProductDAO;
import com.fashion.dao.SupplierDAO;
import com.fashion.domain.Category;
import com.fashion.domain.Product;
import com.fashion.domain.Supplier;
import com.fashion.util.FileUtil;

@Controller
public class ProductController {

	private static Logger log=LoggerFactory.getLogger(ProductController.class);
	
	private ProductDAO productDAO;
	
	private CategoryDAO categoryDAO;
	
	private Product product;
	
	private SupplierDAO supplierDAO;
	
	
	private String path ="F:\\Workspace\\FashionFrontEnd\\src\\main\\webapp\\resource\\image";
	
	@RequestMapping(value="/manage_products" ,method=RequestMethod.GET)
	public String listProducts(Model model)
	{
	log.debug("Starting of the method listProducts");
	model.addAttribute("product",new Product());
	model.addAttribute("supplier",new Supplier());
	model.addAttribute("productList", this.productDAO.list());
	model.addAttribute("isAdminClickedProducts", "true");
	log.debug("Ending of the method listProducts");
	return "/Home";
	}
	
	@RequestMapping(value="/manage_product_add",method=RequestMethod.POST)
	public String addProduct(@ModelAttribute("product") Product product, @RequestParam("image") MultipartFile file,Model model)
	{
		
		log.debug("Starting of the method addProduct");
		Category category=categoryDAO.getcategorybyname(product.getCategory().getName());
		
		Supplier supplier=supplierDAO.getSupplierbyname(product.getSupplier().getName());
		
		product.setCategory(category);
		product.setSupplier(supplier);
		product.setCategory_id(category.getId());
		product.setSupplier_id(supplier.getId());
		productDAO.save(product);
		
		
		FileUtil.upload(path, file, product.getId()+".jpg");
		log.debug("Ending of the method addProduct");
		model.addAttribute("isAdminClickedProducts", true);
		model.addAttribute("productList", this.productDAO.list());
		model.addAttribute("product", new Product());
		model.addAttribute("categoryList", this.categoryDAO.list());
		model.addAttribute("category", new Category());
		
		return "/Home";
		
		
		
	}
	//delete product
	@RequestMapping("/manage_product_delete/{id}")
	public String deleteProduct(@PathVariable("id") String id, ModelMap model) throws Exception
	{
		log.debug("Starting of the method removeProduct");
		try{
			productDAO.delete(id);
			model.addAttribute("message", "Successfully deleted");
			}
		catch(Exception e)
		{
			model.addAttribute("message", e.getMessage());
			e.printStackTrace();
			}
		
		log.debug("Ending of the method removeProduct");
		return "redirect:/manageProducts";
	}
	@RequestMapping("/manage_product_edit/{id}")
	public String editProduct(@PathVariable("id") String id, Model model){
		log.debug("Starting of the method editProduct");
		product=productDAO.getproductbyid(id);
		model.addAttribute("selectedProduct", product);
		log.debug("Ending of the method editProduct");
		return "redirect:/manageProducts";
		
		}
	//get the list products
	@RequestMapping("/manage_product_get/{id}")
	public ModelAndView getSelectedProduct(@PathVariable("id") String id, RedirectAttributes redirectAttributes)
	{
		log.debug("Starting of the method getSeletcedProduct");
		ModelAndView mv= new ModelAndView("redirect:/");
		redirectAttributes.addFlashAttribute("selectedProduct", productDAO.getproductbyid(id));
		log.debug("Ending of the method getSelectedProduct");
		return mv;
	}
	
}
