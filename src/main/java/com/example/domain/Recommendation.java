package com.example.domain;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "Recommends")
public class Recommendation {
	
	@Id @GeneratedValue private Long id;
	
	@EndNode
	private Product product;
	
	@StartNode
	private User user;
	
	private short stars;

	@SuppressWarnings("unused")
	private Recommendation() {	}
	
	public Recommendation(Product product, User user, short stars) {
		this.product = product;
		this.user = user;
		this.stars = stars;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public short getStars() {
		return stars;
	}

	public void setStars(short stars) {
		this.stars = stars;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
