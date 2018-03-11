package com.example.domain;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class User {

	@Id @GeneratedValue private Long id;

	private String name;
	
	private String password;
	
	private String Role;

	@SuppressWarnings("unused")
	private User() {
		// Empty constructor required as of Neo4j API 2.0.5
	};

	@Relationship(type = "Trusts", direction = Relationship.UNDIRECTED)
	private Set<TrustRelationship> trustee = new HashSet<>();
	
	public void addTrustee(TrustRelationship person) {
		if (trustee == null) {
			trustee = new HashSet<>();
		}
		trustee.add(person);
	}
	
	public User(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return Role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		Role = role;
	}
	
}
