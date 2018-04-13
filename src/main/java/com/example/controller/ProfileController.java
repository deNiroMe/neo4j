package com.example.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.domain.TrustRelationship;
import com.example.domain.User;
import com.example.repository.UserRepository;
import com.example.rest.model.UserRestModel;

@Controller
public class ProfileController {

	private UserRepository userRepository;
	
	@Autowired
	public ProfileController(UserRepository userRepository) {
		this.userRepository  = userRepository;
	}
	
	@RequestMapping({"/profile","/"})
	public String profilePage(Principal princibal,Model model) {
		User user = userRepository.findByName(princibal.getName());
		model.addAttribute("user", user);
		return "profile/profile";
	}
	
	@RequestMapping("/profile2")
	public String profile2Page(Principal princibal,Model model) {
		User user = userRepository.findByName(princibal.getName());
		model.addAttribute("user", user);
		return "profile/list-relationships";
	}
	
	@RequestMapping(value="/ajax/add/rt", method=RequestMethod.POST)
	public String createNewTrustRelationship(Principal princibal,String name,@RequestParam("trust_weight")int weight) {
		User user = userRepository.findByName(princibal.getName());
		User trustee = userRepository.findByName(name);
		System.out.println(user);
		System.out.println(trustee);
		System.out.println(weight);
		TrustRelationship tr = new TrustRelationship(weight,user,trustee);
		user.addTrustee(tr);
		userRepository.save(user);
		return "redirect:/profile";
	}
	
	@RequestMapping("/suggestions")
	@ResponseBody
	public List<User> trustRelationshipSuggestion(Principal princibal) {
		List<User> suggestions = userRepository.findPeopleWithNoTrustRelationship(princibal.getName());
		suggestions.removeIf( u -> u.getName().equals(princibal.getName()));
		return suggestions;
	}
	
	@RequestMapping("/friends")
	@ResponseBody
	public List<UserRestModel> trustRelationshipExists(Principal princibal) {
		List<UserRestModel> suggestion = new ArrayList<>();
		suggestion = userRepository
		.getTrustRelationships(princibal.getName())
		.stream().map( t -> {
			int in = t.getWeight();
			Optional<Integer> op = Optional.ofNullable(userRepository.getTrusteeWeight(t.getTrustee().getName(),princibal.getName()));
			int out = op.isPresent() ? op.get().intValue() : 0;
			return new UserRestModel(t.getTrustee(),in,out);
		}).collect(Collectors.toList());
		
		return suggestion;
	}
	
}
