package com.example.service;

import com.example.domain.User;

public interface UserService {
	
	public User findByName(String email);
	
}
