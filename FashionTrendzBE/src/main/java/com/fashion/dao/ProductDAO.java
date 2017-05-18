package com.fashion.dao;

import java.util.List;

import com.fashion.domain.Product;

public interface ProductDAO {
	
	//create product
	public boolean save(Product product);
	
	//update product
	public boolean update(Product product);
	
	//delete product
	public boolean delete(String  id);
	
	//get all product
	public List<Product> list();
	
	//get product by id
	public Product getproductbyid(String id);
	
	//get product by name
	public Product getproductbyname(String name);
	
	public List<Product> getAllProductsbyCategoryId(String categoryId);
	public List<Product> getAllProductsbySupplierId(String supplierId);
	


}
