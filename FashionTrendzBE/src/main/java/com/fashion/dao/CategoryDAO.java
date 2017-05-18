package com.fashion.dao;

import java.util.List;

import com.fashion.domain.Category;

public interface CategoryDAO {
	
		
	
	//create category
	public boolean save(Category category);
	
	//update category
	public boolean update(Category category);
	
	
	 //get all the category list
	public List<Category> list();
	
    //delete category
	public boolean delete(String id);
	
	//get category by id
	public Category getcategorybyid(String id);
	
	//get category by name
	public Category getcategorybyname(String name);
	
	
	
}
