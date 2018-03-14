package com.example.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

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
	public List<User> trustRelationshipExists(Principal princibal) {
		List<User> suggestions = new ArrayList<>();
		for(TrustRelationship t : userRepository
									.getTrustRelationships(princibal.getName())) {
			suggestions.add(t.getTrustee());
		}
		return suggestions;
	}
	
}