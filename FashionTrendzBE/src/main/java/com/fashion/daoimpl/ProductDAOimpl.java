package com.fashion.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fashion.dao.ProductDAO;
import com.fashion.domain.Product;

@Repository("productDAO")
@Transactional
public class ProductDAOimpl implements ProductDAO {
	
	@Autowired private SessionFactory sessionFactory;
	
	public ProductDAOimpl(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}

	public boolean save(Product product) { 
		try
		{
		sessionFactory.getCurrentSession().save(product);
	    }
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
   }

	public boolean update(Product product) {
		try{
			sessionFactory.getCurrentSession().update(product);
			}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
			
		}
		return true;
	}

	public boolean delete(String id) {
		try{
			sessionFactory.getCurrentSession().delete(getproductbyid(id));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	    return true;
	}
	
	

	public List<Product> list() {
		 return sessionFactory.getCurrentSession().createQuery("from Product").list();
	}

	public Product getproductbyid(String id) {
		return (Product) sessionFactory.getCurrentSession().get(Product.class, id);
	}

	public Product getproductbyname(String name) {
		Query query=sessionFactory.getCurrentSession().createQuery("from Product where name ?");
		query.setString(0, name);
		return (Product) query.uniqueResult();
	}

	
	public List<Product> getAllProductsbyCategoryId(String categoryId) {
		String hql ="from Product where category_id=?";
		Query query= sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, categoryId);
		return query.list();
	}


	public List<Product> getAllProductsbySupplierId(String supplierId) {
		return sessionFactory.getCurrentSession().createQuery("from Product where supplier_id =?").setString(0,supplierId).list();
		
	}

	
	
}
