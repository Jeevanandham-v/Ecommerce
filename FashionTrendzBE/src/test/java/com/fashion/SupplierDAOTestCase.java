package com.fashion;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fashion.dao.SupplierDAO;
import com.fashion.domain.Supplier;




public class SupplierDAOTestCase {

	@Autowired static AnnotationConfigApplicationContext context;
	
	@Autowired static SupplierDAO supplierDAO;
	
	@Autowired static Supplier supplier;
	
	@BeforeClass
	public static void intilaze()
	{
		context= new AnnotationConfigApplicationContext();
		context.scan("com.fashion");
		context.refresh();
		
		supplierDAO= (SupplierDAO) context.getBean("supplierDAO");
		
		supplier = (Supplier) context.getBean("supplier");
		
	}
	@Test
	public void getallsupplierTestcase(){
		List <Supplier> suppliers= supplierDAO.list();
		assertEquals(4,suppliers.size());
	}
	@Test
	public void createsupplierTestcase()
	{
		supplier.setId("s7");
		supplier.setName("adidas");
		supplier.setAddress("usa");
		boolean flag= supplierDAO.save(supplier);
		assertEquals("createsupplierTestcase",true,flag);
		
	}
	@Test
	public void updateSupplierTestcase()
	{
		supplier.setId("s06");
		supplier.setName("Twin");
		supplier.setAddress("usa");
		boolean flag=supplierDAO.update(supplier);
		assertEquals("updateSupplierTestcase",true,flag);
	}
	
	@Test
	public void deleteSupplierTestCase(){
		supplier.setId("s200");
		boolean flag=supplierDAO.delete("s200");
		assertEquals("deleteSupplierTestcase",true,flag);
		
		
	}
}
