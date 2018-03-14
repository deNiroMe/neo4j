package com.example.forms;

import java.util.Arrays;

import com.example.domain.User;

public class RegistrationForm {
	
	private String name; 
	
	private String email;
	
	private String password;
	
	private String description;
	
	private String[] intrests;
	
	private String job;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public User createUser(){		
		User user = new User(this.name);
		user.setPassword(this.password);
		user.setRole("USER");
		user.setDescription(this.description);
		user.setJob(this.job);
		user.setEmail(this.email);
		user.setIntrests(this.intrests);
		return user;
	}
	
	@Override
	public String toString() {
		return "RegistrationForm [name=" + name + ", email=" + email + ", password=" + password + ", description="
				+ description + ", intrests=" + Arrays.toString(intrests) + ", job=" + job + "]";
	}
	
}
