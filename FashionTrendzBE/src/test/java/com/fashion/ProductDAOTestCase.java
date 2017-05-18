package com.fashion;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fashion.dao.ProductDAO;
import com.fashion.domain.Product;

public class ProductDAOTestCase {
	
	@Autowired static AnnotationConfigApplicationContext context;
   
	@Autowired static ProductDAO productDAO;
	
	@Autowired static Product product;
	
	/*the above objects need to initiallize
	
	this method is going execute before calling any one of test case 
	and will execute only once
*/
	@BeforeClass
	public static void initialize()
	{
		context= new AnnotationConfigApplicationContext();
		context.scan("com.fashion");
		context.refresh();
		
		//get the product context
		productDAO = (ProductDAO) context.getBean("productDAO");
		
		product= (Product) context.getBean("product");
	}
    @Test
	public void createProductTestCase()
	{
		product.setId("p13");
		product.setName("jeans");
		product.setPrice("1099");
		product.setCategory_id("f101");
		product.setDescription("women product");
		product.setSupplier_id("s01");
		boolean flag =  productDAO.save(product);

		
		
		//error - if there is in runtime errors  -  Red mark
				//success  - if expected and actual is same  - green mark
				//fail  - if expected and actual is different  -  blue mark
	assertEquals("createProductTestCase",true,flag);
	
				
	}
	
    @Test
    public void updateProductTestCase()
    {
    	product.setId("p10");
    	product.setName("legging");
    	product.setPrice("500");
    	product.setCategory_id("f101");
    	product.setSupplier_id("s06");
    	
    	boolean flag=productDAO.update(product);
    	//test case 
    	//error - if there is in runtime errors  -  Red mark
    			//success  - if expected and actual is same  - green mark
    			//fail  - if expected and actual is different  -  blue mark
    	assertEquals(" update product test case",true,flag);
    			
    	 }
  
    
    @Test
    public void getallProductTestCase()
    {
    int actualSize= productDAO.list().size();
    assertEquals("10", actualSize);
    }

    @Test
    public void  deleteProductTestcase()
    {
    	product.setId("p17");
    	boolean flag=productDAO.delete("p17");
    	assertEquals("deleteProductTestcase",true,flag);
    			
    }

}


