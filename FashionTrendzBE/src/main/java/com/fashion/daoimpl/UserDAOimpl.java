package com.fashion.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fashion.dao.UserDAO;
import com.fashion.domain.User;
@Repository("userDAO")
@Transactional
public class UserDAOimpl implements UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	//write user defined constructor with one parameter i.e.., sessionFactory
	
	public UserDAOimpl(SessionFactory sessionFactory){
		this.sessionFactory= sessionFactory;
	}
	
	
	/*this save method create record in the User table.
	if the record is created successfully,it will return true
	else will return false
*/
	
	public boolean save(User user) {
		try{
			sessionFactory.getCurrentSession().save(user);
		}catch(Exception e){
			//if any exception comes during execute of try block,catch will execute
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/*this update method update record in the User table.
	if the record is update successfully,it will return true
	else will return false*/


	public boolean update(User user) {
		try{
			sessionFactory.getCurrentSession().update(user);
		}catch(Exception e){
			//if any exception comes during execute of try block,catch will execute
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	/*validate method will return true if the credetails are correct 
	else will return false*/

	public boolean validate(String id, String password) {
		Query query= sessionFactory.getCurrentSession().createQuery("from User where id=? and password=?");
	    query.setString(0,id);  
	    query.setString(1,password);
	    
	    //in the user table with this id and password there will one or zero records will exist
	    //i.e., uniqueResult
	    //uniqueResult method will return object if a row exist, else it will return null
	    if(query.uniqueResult()==null){
	    	//it means no row exist i.e., invalid credentials
	    	return false;
	    }
	    else{
	    	//it means row exist i.e., valid credentials
	    	return true;
	    }
	}

	public List<User> list() {
		return sessionFactory.getCurrentSession().createQuery("from User").list();
	}

	public User get(String id) {
		//getmethod get the data from user table based on primary key i.e., id
		//and set it to user class
		//like select *from user where id=?
		return (User) sessionFactory.getCurrentSession().get(User.class, id);
	}
	
	

}
