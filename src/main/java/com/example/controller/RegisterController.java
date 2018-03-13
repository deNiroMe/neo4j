package com.example.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.domain.TrustRelationship;
import com.example.domain.User;
import com.example.forms.RegistrationForm;
import com.example.repository.UserRepository;

@Controller
public class RegisterController {
	
	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/register")
	public String registrationPage() {
		return "register/register";
	}
	
	@RequestMapping("/register/json")
	@ResponseBody
	public List<User> getjson() {
		return userRepository.findUserFriends("Greg");
	}

	@RequestMapping("/register/json2")
	@ResponseBody
	public List<TrustRelationship> getTrustjson() {
		return userRepository.getTrustRelationships("Greg");
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String regiter(@ModelAttribute("registrationForm") RegistrationForm form) {
		System.out.println(form);
		userRepository.save(form.createUser());
		return "register/register";
	}

	@ModelAttribute("registrationForm")
	public RegistrationForm getRegistrationForm() {
		return new RegistrationForm();
	}

	@ModelAttribute("intrests")
	public List<String> getIntrestsList() {
		return Arrays.asList("Development", "Design", "Business");
	}
}
