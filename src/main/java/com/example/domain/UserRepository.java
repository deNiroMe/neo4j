package com.example.domain;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByName(String name);

    @Query("MATCH (u:User {name: {0}})-[:Trusts]->(y:User) RETURN y")
    List<User> findUserFriends(String name);
    
    @Query("MATCH (u:User {name: {0}})-[t:Trusts]->(y:User) return t,u,y")
    List<TrustRelationship> getTrustRelationships(String name);   
    
}
