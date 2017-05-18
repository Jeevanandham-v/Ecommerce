package com.fashion.daoimpl;

import java.util.List;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fashion.dao.SupplierDAO;
import com.fashion.domain.Supplier;


@Repository("supplierDAO")
@Transactional
public class SupplierDAOimpl  implements SupplierDAO {

@Autowired  private SessionFactory sessionFactory;

  public SupplierDAOimpl(SessionFactory sessionFactory)
  {
	  this.sessionFactory=sessionFactory;
	 
  }
	
	
	
	public boolean save(Supplier supplier) {
		try{
		sessionFactory.getCurrentSession().save(supplier);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean update(Supplier supplier) {
		
		try{
			sessionFactory.getCurrentSession().update(supplier);
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
			sessionFactory.getCurrentSession().delete(getsupplierbyid(id));
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}



	public List<Supplier> list() {
		
		return sessionFactory.getCurrentSession().createQuery("from Supplier").list();
	}



	public Supplier getsupplierbyid(String id) {
		return (Supplier) sessionFactory.getCurrentSession().get(Supplier.class, id);
		
	}



	public Supplier getSupplierbyname(String name) {
		Query query =sessionFactory.getCurrentSession().createQuery("from Supplier where name ?");
		query.setString(0, name);
		 return (Supplier) query.uniqueResult();
	}

}	
	