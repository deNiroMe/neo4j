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
	
	private String city;
	
	private String gender;
	
	private String job;
	
	private String photo;

	@SuppressWarnings("unused")
	private User() {
		// Empty constructor required as of Neo4j API 2.0.5
	};

	@Relationship(type = "Trusts", direction = Relationship.UNDIRECTED)
	private Set<TrustRelationship> trustee = new HashSet<>();
	
	@Relationship(type = "Recommends", direction = Relationship.UNDIRECTED)
	private Set<Recommendation> recommendations = new HashSet<>();	
	
	public void addTrustee(TrustRelationship person) {
		trustee.add(person);
	}
	
	public void addRecommendation(Recommendation person) {
		recommendations.add(person);
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
	public User(String name, String password, String role, String email, String city, String gender,
			String job) {
		super();
		this.name = name;
		this.password = password;
		Role = role;
		this.email = email;
		this.city = city;
		this.gender = gender;
		this.job = job;
	}
	
	

	public User(String name, String password, String role, String email, String city, String gender, String job,
			String photo) {
		this.name = name;
		this.password = password;
		Role = role;
		this.email = email;
		this.city = city;
		this.gender = gender;
		this.job = job;
		this.photo = photo;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	

}
