package com.fashion.domain;

/*import java.util.Set;*/

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;

@Entity  //to map database to the table
@Component
public class Category {
	
	//define the all properties for all field in table
	//to mention which one is primary key
	@Id
	private String id;
	
	private String name;
	
	private String description;
	
	/*private Set<Product> products;*/
	
 /*  @OneToMany(mappedBy="category" ,fetch=FetchType.EAGER)
	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}*/

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

}
