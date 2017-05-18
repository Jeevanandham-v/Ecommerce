package com.fashion;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fashion.dao.CategoryDAO;
import com.fashion.domain.Category;


public class CategoryTestCase {

	@Autowired static AnnotationConfigApplicationContext context;
	
	@Autowired static CategoryDAO categoryDAO;
	
	@Autowired static Category category;
	
	@BeforeClass
	public static void intilaze()
	{
		context= new AnnotationConfigApplicationContext();
		context.scan("com.fashion");
		context.refresh();
		
		categoryDAO= (CategoryDAO) context.getBean("categoryDAO");
		
		category = (Category) context.getBean("category");
		
	}
	@Test
	public void getallCategoryTestcase(){
		List <Category> categories= categoryDAO.list();
		assertEquals(4,categories.size());
	}
	@Test
	public void createcategoryTestcase()
	{
		category.setId("k02");
		category.setName("Kids");
		category.setDescription("kids product");
		boolean flag =categoryDAO.save(category);
		
		assertEquals("createcategoryTestcase",true,flag);
	}
    
	@Test
	public void deleteCategoryTestCase()
	{
		category.setId("k02");
		boolean flag=categoryDAO.delete("k02");
		assertEquals("deleteCategoryTestCase",true,flag);
		
		
	}

 }
