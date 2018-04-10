package com.example.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.domain.Product;
import com.example.domain.Recommendation;

public interface ProductRepository extends CrudRepository<Product,Long> {

	 @Query("MATCH (u:User {name: {0}})-[t:Recommends]->(y:Product) return t,u,y")
	 List<Recommendation> getUserRecommendations(String name);    
	
	 @Query("MATCH (u:User)-[t:Recommends]->(y:Product {name: {0}}) return t,u,y")
	 List<Recommendation> peopleWhoAlsoLiked(String name);    	
	 
	 Product findByName(String name) ;
}
