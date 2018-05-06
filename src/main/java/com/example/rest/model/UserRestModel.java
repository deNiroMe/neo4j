package com.example.rest.model;

import com.example.domain.User;

public class UserRestModel {

	private Long id;

	private String name;
	
	private String password;
	
	private String Role;
	
	private String email;
	
	private String city;
	
	private String gender;
	
	private String job;
	
	private String photo;
	
	private int weightIn;
	
	private int weightOut;
	
	private boolean up;

	public UserRestModel(User user, int weightIn, int weightOut) {
		super();
		this.name = user.getName();
		this.password = user.getPassword();
		this.Role = user.getRole();
		this.email = user.getEmail();
		this.city = user.getCity();
		this.gender = user.getGender();
		this.job = user.getJob();
		this.photo = user.getPhoto();
		this.weightIn = weightIn;
		this.weightOut = weightOut;
	}
	
	public UserRestModel(User user, int weightIn, int weightOut,boolean up) {
		super();
		this.name = user.getName();
		this.password = user.getPassword();
		this.Role = user.getRole();
		this.email = user.getEmail();
		this.city = user.getCity();
		this.gender = user.getGender();
		this.job = user.getJob();
		this.photo = user.getPhoto();
		this.weightIn = weightIn;
		this.weightOut = weightOut;
		this.up = up;
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

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	/**
	 * @return the up
	 */
	public boolean isUp() {
		return up;
	}

	/**
	 * @param up the up to set
	 */
	public void setUp(boolean up) {
		this.up = up;
	}

	
}
