package com.fashion.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fashion.dao.CategoryDAO;
import com.fashion.domain.Category;


@Repository("categoryDAO") //to create singleton instance
//like categoryDAO categoryDAO=new categoryDAO()
@Transactional
public class CategoryDAOimpl implements CategoryDAO{

	
@Autowired private SessionFactory sessionFactory;
	
	
	public CategoryDAOimpl(SessionFactory sessionFactory) 
	
	{
		this.sessionFactory=sessionFactory;
		
	}
	
	/*this save method create record in the category table.
	if the record is created successfully,it will return true
	else will return false*/

	
	public boolean save(Category category) {
		try{
			sessionFactory.getCurrentSession().save(category);
			}
	  	catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/*this update method update record in the category table.
	if the record is update successfully,it will return true
	else will return false
*/


	public boolean update(Category category) {
		try{
			sessionFactory.getCurrentSession().update(category);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
			
		}
		return true;
	}

	public List<Category> list() {
		return sessionFactory.getCurrentSession().createQuery("from Category").list();
	
	}

	public boolean delete(String id) {
		try
		{
		sessionFactory.getCurrentSession().delete(getcategorybyid(id));
		}catch(Exception e){
			e.printStackTrace();
			return false;
			}
		return true;
	}

	public Category getcategorybyid(String id) {
		 return (Category) sessionFactory.getCurrentSession().get(Category.class, id);
	
	}

	public Category getcategorybyname(String name) {
		
		Query query= sessionFactory.getCurrentSession().createQuery("from Category where name=?");
		query.setString(0, name);
		 
		 return (Category) query.uniqueResult();
	}

}
