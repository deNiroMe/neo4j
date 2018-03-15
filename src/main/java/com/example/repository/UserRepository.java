package com.example.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.domain.TrustRelationship;
import com.example.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByName(String name);

    @Query("MATCH (u:User {name: {0}})-[:Trusts]->(y:User) RETURN y")
    List<User> findUserFriends(String name);
    
    @Query("MATCH (u:User {name: {0}})-[t:Trusts]->(y:User) return t,u,y")
    List<TrustRelationship> getTrustRelationships(String name); 
    
    @Query("MATCH (u:User {name: {0}})-[t:Trusts]->(y:User {name: {1}}) return t,u,y")
    TrustRelationship getTrustee(String name,String trustee); 
    
    @Query("MATCH (u:User {name: {0}})-[t:Trusts]->(y:User {name: {1}}) return t.weight")
    Integer getTrusteeWeight(String name,String trustee); 
    
    // match (u) where not (u:User)-[:Trusts]->(:User {name:"hind"}) return u 
    @Query("match (u) where (u:User)-[:Trusts]->(:User {name:{0}}) return u")
    List<User> findPeopleWithTrustRelationship(String name);    
    
    // match (u) where not (u)<-[:Trusts]-(:User {name:"hind"}) return u
    @Query("match (u) where not (u)<-[:Trusts]-(:User {name:{0}}) return u")
    List<User> findPeopleWithNoTrustRelationship(String name);
}
