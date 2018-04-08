package com.example.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.repository.UserRepository;

@Controller
public class ProductsController {
	
private UserRepository userRepository;
	
	@Autowired
	public ProductsController(UserRepository userRepository) {
		this.userRepository  = userRepository;
	}

	@RequestMapping("/products")
	public String productsPage(Principal princibal,Model model) {
		User user = userRepository.findByName(princibal.getName());
		model.addAttribute("user", user);
		return "products/product-list";
	}
	
}
