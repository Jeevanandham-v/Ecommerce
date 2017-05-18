package com.fashion.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;


@Entity  //to map to database table
@Table(name="User")//if the table name and domain class name is different
@Component //context.scan("com.fashion")  --will scan the package and create singleton instance 
public class User {
	
	//we have define all properties for all fields in tables
	
	//we have mention which one is primary key
	@Id 
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	private String name;

	private String password;
	
	private String contact;
	
	private String role;

}
