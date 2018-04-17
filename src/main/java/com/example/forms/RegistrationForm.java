package com.example.forms;

import java.util.Random;

import com.example.domain.User;

public class RegistrationForm {
	
	private String name; 
	
	private String email;
	
	private String password;
	
	private String city;
	
	private String gender;
	
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

	public User createUser(){		
		User user = new User(this.name);
		user.setPassword(this.password);
		user.setRole("USER");
		user.setCity(this.city);
		user.setJob(this.job);
		user.setEmail(this.email);
		user.setGender(this.gender);
		
			
		int n =	user.getGender().equals("M") ? getRandomNumberInRange(21, 27) : getRandomNumberInRange(13, 19);
		
		user.setPhoto("img/avatar"+n+".png");
		
		return user;
	}
	
	@Override
	public String toString() {
		return "RegistrationForm [name=" + name + ", email=" + email + ", password=" + password + ", city="
				+ city + ", gender=" + gender + ", job=" + job + "]";
	}
	
	
	private int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	
}
