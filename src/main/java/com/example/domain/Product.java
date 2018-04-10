package com.example.domain;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Product {
	
	@Id @GeneratedValue private Long id;
	
	private String name;
	
	private String category;
	
	private String price;
	
	private short photo;
	
	@SuppressWarnings("unused")
	private Product() {
		// Empty constructor required as of Neo4j API 2.0.5
	};


	public Product(String name, String category, String price, short photo) {
		super();
		this.name = name;
		this.category = category;
		this.price = price;
		this.photo = photo;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public short getPhoto() {
		return photo;
	}

	public void setPhoto(short photo) {
		this.photo = photo;
	}
	
}
