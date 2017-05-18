package com.fashion.dao;

import java.util.List;

import com.fashion.domain.Supplier;

public interface SupplierDAO {
	
	
	//create supplier
	public boolean save(Supplier supplier);
	
	//update supplier
	public boolean update(Supplier supplier);
	
	//delete supplier
	public boolean delete(String id);
	
	//get all supplier
	public List<Supplier> list();
	
	//get supplier by id
	public Supplier getsupplierbyid(String id);
	
	//get supplier by name
	public Supplier getSupplierbyname(String name);
	

}
