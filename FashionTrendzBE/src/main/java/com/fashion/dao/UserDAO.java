package com.fashion.dao;

import java.util.List;

import com.fashion.domain.User;
public interface UserDAO {
	
	//declare methods
	
	//what type of operation you are going to do for user
	
	//operations
	//1)create/register-save
	//2)update the user details-update
	//3)validate the credentials-validate
	//4)get all users-list
	
	//declare the methods with proper signature
	//access_specifier return_type methodname(parameter_list) throws expection_list
	
	
	//create/register
	
	public boolean save(User user);
	
	//update the user details-update
	public boolean update(User user);
	
	
	//validate the credentials - validate
	public boolean validate(String id,String password);

	//get all users-list
	public List<User> list();
	
	//get user details based on userID
	public User get(String id);
	
	
}
