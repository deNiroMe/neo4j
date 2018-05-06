package com.example.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.domain.Product;
import com.example.domain.Recommendation;
import com.example.domain.TrustRelationship;
import com.example.domain.User;
import com.example.repository.ProductRepository;
import com.example.repository.RecommendationRepository;
import com.example.repository.UserRepository;
import com.example.rest.model.UserRestModel;

@Controller
public class ProductsController {

	private UserRepository userRepository;

	private ProductRepository productRepository;

	@Autowired
	RecommendationRepository recommendationRepository;

	@Autowired
	public ProductsController(UserRepository userRepository, ProductRepository productRepository) {
		this.userRepository = userRepository;
		this.productRepository = productRepository;
	}

	@RequestMapping("/products")
	public String productsPage(Principal princibal, Model model) {
		User user = userRepository.findByName(princibal.getName());
		model.addAttribute("user", user);
		return "products/product-list";
	}

	@RequestMapping("/my-recommendations")
	public String recommendationsPage(Principal princibal, Model model) {
		User user = userRepository.findByName(princibal.getName());
		model.addAttribute("user", user);
		return "products/recommendations";
	}

	@RequestMapping("/ajax/products")
	@ResponseBody
	public List<Product> products(Principal princibal, Model model) {
		return (List<Product>) productRepository.findAll();
	}

	@RequestMapping("/ajax/recommendation")
	@ResponseBody
	public List<Recommendation> recommendation(Principal princibal) {
		return productRepository.getUserRecommendations(princibal.getName());
	}

	@RequestMapping("/ajax/add-recommendation")
	@ResponseBody
	public List<Recommendation> createRecommendation(Principal princibal, @RequestParam String product,
			@RequestParam int starts) {

		Product p = productRepository.findByName(product);
		User user = userRepository.findByName(princibal.getName());

		List<Recommendation> list = productRepository.getUserRecommendations(user.getName());
		boolean exists = false;
		for (Recommendation r : list) {
			if (r.getProduct().getName().equals(product))
				exists = true;
		}
		if (!exists) {
			Recommendation r1 = new Recommendation(p, user, (short) starts);
			user.addRecommendation(r1);
			userRepository.save(user);
		} else {
			Recommendation r1 = productRepository.recommendationByUserAndProduct(user.getName(), product);
			r1.setStars((short) starts);
			recommendationRepository.save(r1);
		}
		List<User> friends = userRepository.getTrustRelationships(user.getName()).stream().map(t -> t.getTrustee())
				.collect(Collectors.toList());
		List<Recommendation> r = productRepository.peopleWhoAlsoLiked(product);
		// r.stream().filter( re -> re.getUser().get)

		return productRepository.peopleWhoAlsoLiked(product);
	}

	@RequestMapping("/ajax/peopleWhoAlsoLiked")
	@ResponseBody
	public List<Recommendation> peopleWhoAlsoLiked(Principal princibal, @RequestParam String product) {
		return productRepository.peopleWhoAlsoLiked(product);
	}

	@RequestMapping("/ajax/trustee-recommend")
	@ResponseBody
	public List<UserRestModel> peopleITrustAndRecommendTheProduct(Principal princibal, @RequestParam String product) {

		User user = userRepository.findByName(princibal.getName());
		List<User> list = productRepository.peopleWhoAlsoLiked(product).stream().map(t -> t.getUser())
				.collect(Collectors.toList());
		List<User> friends = userRepository.getTrustRelationships(user.getName()).stream().map(t -> t.getTrustee())
				.collect(Collectors.toList());

		
		List<UserRestModel> suggestion = friends.stream()
		.filter( f -> !list.contains(f) )
		.map( t -> {
			TrustRelationship trust = userRepository.getTrustee(princibal.getName(), t.getName());
			int in = trust.getWeight();
			Optional<Integer> op = Optional.ofNullable(userRepository.getTrusteeWeight(trust.getTrustee().getName(),princibal.getName()));
			int out = op.isPresent() ? op.get().intValue() : 0;
			return new UserRestModel(trust.getTrustee(),in,out,false);
		}).collect(Collectors.toList());
		
		suggestion.addAll( list.stream()
		.filter(o -> userRepository.getTrustee(princibal.getName(), o.getName())==null?false:true) 
		.map( t -> {
			TrustRelationship trust = userRepository.getTrustee(princibal.getName(), t.getName());
			int in = trust.getWeight();
			Optional<Integer> op = Optional.ofNullable(userRepository.getTrusteeWeight(trust.getTrustee().getName(),princibal.getName()));
			int out = op.isPresent() ? op.get().intValue() : 0;
			return new UserRestModel(trust.getTrustee(),in,out,true);
		}).collect(Collectors.toList()) );
		return suggestion;
		
//		List<UserRestModel> suggestion = new ArrayList<>();
//		suggestion = userRepository
//		.getTrustRelationships(princibal.getName())
//		.stream()
//		.filter(o -> friends.contains(o))
//		.map( t -> {
//			int in = t.getWeight();
//			Optional<Integer> op = Optional.ofNullable(userRepository.getTrusteeWeight(t.getTrustee().getName(),princibal.getName()));
//			int out = op.isPresent() ? op.get().intValue() : 0;
//			return new UserRestModel(t.getTrustee(),in,out,true);
//		}).collect(Collectors.toList());
//		
//		List<UserRestModel> suggestion2 = new ArrayList<>();
//		suggestion = userRepository
//		.getAllTrustRelationships()
//		.stream()
//		.filter(o -> !friends.contains(o))
//		.map( t -> {
//			int in = t.getWeight();
//			Optional<Integer> op = Optional.ofNullable(userRepository.getTrusteeWeight(t.getTrustee().getName(),princibal.getName()));
//			int out = op.isPresent() ? op.get().intValue() : 0;
//			return new UserRestModel(t.getTrustee(),in,out,false);
//		}).collect(Collectors.toList());
//		
//		//return friends.stream().collect(Collectors.partitioningBy(o -> list.contains(o)));
//		 suggestion.addAll(suggestion2);
//		 return suggestion;
	}
}
