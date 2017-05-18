package com.fashion;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fashion.dao.UserDAO;
import com.fashion.domain.User;

public class UserDAOTestCase {
	
	@Autowired static AnnotationConfigApplicationContext context;
   
	@Autowired static UserDAO userDAO;
	
	@Autowired static User user;
	
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
		//get the user context
		userDAO = (UserDAO) context.getBean("userDAO");
		
		user= (User) context.getBean("user");
	}
    @Test
	public void createUserTestCase()
	{
   
		user.setId("sabaris");
		user.setName("sabaris");
		user.setPassword("sabaris");
		user.setRole("admin");
		user.setContact("20000");
		boolean flag =  userDAO.save(user);

		
		
		//error - if there is in runtime errors  -  Red mark
				//success  - if expected and actual is same  - green mark
				//fail  - if expected and actual is different  -  blue mark
	assertEquals("createUserTestCase",true,flag);
				
	}
	
    @Test
    public void updateUserTestCase()
    {
    	user.setId("jeeva");
    	user.setName("jeevanandham");
    	user.setPassword("jeeva");
    	user.setRole("admin");
    	user.setContact("8220242742");
    	boolean flag =userDAO.update(user);
    
    	//test case 
    	//error - if there is in runtime errors  -  Red mark
    			//success  - if expected and actual is same  - green mark
    			//fail  - if expected and actual is different  -  blue mark
    	assertEquals(" update user test case",true,flag);
    			
    	 }
    @Test
    public void validatelUserTestCase()
    
    {
    	boolean flag=userDAO.validate("jeeva", "jeevan");
    	assertEquals(true,flag);
    }
    
    @Test
    public void getallUserTestCase()
    {
    int actualSize= userDAO.list().size();
    assertEquals("2", actualSize);
    }
    
}
