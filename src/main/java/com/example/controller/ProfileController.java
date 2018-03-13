package com.example.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.domain.User;
import com.example.repository.UserRepository;

@Controller
public class ProfileController {

	private UserRepository userRepository;
	
	@Autowired
	public ProfileController(UserRepository userRepository) {
		this.userRepository  = userRepository;
	}
	
	@RequestMapping("/profile")
	public String profilePage(Principal princibal,Model model) {
		User user = userRepository.findByName(princibal.getName());
		model.addAttribute("user", user);
		return "profile/profile";
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
	public List<User> trustRelationshipExists(Principal princibal) {
		List<User> suggestions = userRepository.findPeopleWithTrustRelationship(princibal.getName());
		suggestions.removeIf( u -> u.getName().equals(princibal.getName()));
		return suggestions;
	}
	
}
