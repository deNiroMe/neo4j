package com.example.rest.model;

import com.example.domain.User;

public class UserRestModel {

	private Long id;

	private String name;
	
	private String password;
	
	private String Role;
	
	private String email;
	
	private String description;
	
	private String[] intrests;
	
	private String job;
	
	private int weightIn;
	
	private int weightOut;

	public UserRestModel(User user, int weightIn, int weightOut) {
		super();
		this.name = user.getName();
		this.password = user.getPassword();
		this.Role = user.getRole();
		this.email = user.getEmail();
		this.description = user.getDescription();
		this.intrests = user.getIntrests();
		this.job = user.getJob();
		this.weightIn = weightIn;
		this.weightOut = weightOut;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getRole() {
		return Role;
	}

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

	public int getWeightIn() {
		return weightIn;
	}

	public void setWeightIn(int weightIn) {
		this.weightIn = weightIn;
	}

	public int getWeightOut() {
		return weightOut;
	}

	public void setWeightOut(int weightOut) {
		this.weightOut = weightOut;
	}
	
}
