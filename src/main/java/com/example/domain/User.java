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
	
	private String email;
	
	private String description;
	
	private String[] intrests;
	
	private String job;

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
	
	
	/**
	 * @param name
	 * @param password
	 * @param role
	 * @param email
	 * @param description
	 * @param intrests
	 * @param job
	 */
	public User(String name, String password, String role, String email, String description, String[] intrests,
			String job) {
		super();
		this.name = name;
		this.password = password;
		Role = role;
		this.email = email;
		this.description = description;
		this.intrests = intrests;
		this.job = job;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String[] getIntrests() {
		return intrests;
	}

	public void setIntrests(String[] intrests) {
		this.intrests = intrests;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

}
