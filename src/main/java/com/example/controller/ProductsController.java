package com.example.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.domain.Product;
import com.example.domain.Recommendation;
import com.example.domain.User;
import com.example.repository.ProductRepository;
import com.example.repository.UserRepository;

@Controller
public class ProductsController {
	
	private UserRepository userRepository;
	
	private ProductRepository productRepository;
	
	@Autowired
	public ProductsController(UserRepository userRepository,ProductRepository productRepository) {
		this.userRepository  = userRepository;
		this.productRepository = productRepository;
	}

	@RequestMapping("/products")
	public String productsPage(Principal princibal,Model model) {
		User user = userRepository.findByName(princibal.getName());
		model.addAttribute("user", user);
		return "products/product-list";
	}
	
	@RequestMapping("/ajax/products")
	@ResponseBody
	public List<Product> products(Principal princibal,Model model) {		
		return (List<Product>) productRepository.findAll();
	}
	
	@RequestMapping("/ajax/recommendation")
	@ResponseBody
	public List<Recommendation> recommendation(Principal princibal,@RequestParam String name) {		
		return  productRepository.getUserRecommendations(name);
	}
	
	@RequestMapping("/ajax/add-recommendation")
	@ResponseBody
	public List<Recommendation> createRecommendation(Principal princibal,@RequestParam String product,@RequestParam int starts) {
		
		Product p = productRepository.findByName(product);
		User user = userRepository.findByName(princibal.getName());
		
		List<Recommendation> list = productRepository.getUserRecommendations(user.getName());
		boolean exists = false;
		for(Recommendation r : list) {
			if(r.getProduct().getName().equals(product))
				exists = true;
		}
		if(!exists) {
			Recommendation r1 = new Recommendation(p, user,(short) starts);
			user.addRecommendation(r1);
			userRepository.save(user);
		}
		
		return  productRepository.peopleWhoAlsoLiked(product);
	}
	
	@RequestMapping("/ajax/peopleWhoAlsoLiked")
	@ResponseBody
	public List<Recommendation> peopleWhoAlsoLiked(Principal princibal,@RequestParam String product) {		
		return  productRepository.peopleWhoAlsoLiked(product);
	}
	
}
