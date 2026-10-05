package com.springboot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.Dao.UserRepository;
import com.springboot.entity.User;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepositoy;

	public UserController(UserRepository userRepositoy) {
		super();
		this.userRepositoy = userRepositoy;
	}

	@GetMapping
	public User greet() {
		System.out.println("UserController.greet : ");
		return new User(99, "Dummy", "No", "Planet Not Found");

	}

	@GetMapping("/path/{id}")
	public Optional<User> pathVariablle(@PathVariable(name = "id") int id) {
		System.out.println("UserController.pathVariablle : " + id);
		return userRepositoy.findById(id);
	}

	@GetMapping("/all-users")
	public List<User> getAllUsers() {
		System.out.println("UserController.getAllUsers()");
		return userRepositoy.findAll();
	}

	@PostMapping
	public User saveUser(@RequestBody User user) {
		System.out.println("UserController.saveUser : ");
		System.out.println(user);
		userRepositoy.save(user);
		return user;
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteuser(@PathVariable(name = "id") int id)
	{
		
		System.out.println("deleteuser()"+id);
		userRepositoy.deleteById(id);
		return "userdeleted succesfully";
	}
	
	@PutMapping("/{id}")
	public User updateUser(
	        @PathVariable int id,
	        @RequestBody User updatedUser) {

	    Optional<User> optionalUser = userRepositoy.findById(id);

	    if (optionalUser.isPresent()) {

	        User user = optionalUser.get();

	        user.setName(updatedUser.getName());
	        user.setGender(updatedUser.getGender());
	        user.setAddress(updatedUser.getAddress());

	        return userRepositoy.save(user);
	    }

	    return null;
	}

	
	}