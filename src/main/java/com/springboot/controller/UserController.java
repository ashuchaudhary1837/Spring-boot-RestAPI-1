package com.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.springboot.entity.User;

@Controller
public class UserController {


	@GetMapping
	public String greet() {
		System.out.println("UserController.greet : ");
		return "home";

	}

}