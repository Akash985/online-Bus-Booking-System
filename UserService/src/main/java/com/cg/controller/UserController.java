package com.cg.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.cg.exception.UserNotFoundException;
import com.cg.model.User;
import com.cg.service.UserServiceImpl;


import io.swagger.annotations.Api;

@RestController
@RequestMapping("/user")
@Api(value="Bus Booking Application")
public class UserController {
	
	@Autowired
	UserServiceImpl userService;
	
	@GetMapping("/userdetails")
	public List<User> getAllUserDetails()
	{
		return userService.getAllUserDetails();
	}
	
	
	@GetMapping("/userdetails/{userid}")
	public User getUserDetailsByBusId(@PathVariable("userid") Long userId ) throws UserNotFoundException {
		
		return userService.getUserDetailsByUserId(userId);
		
	}
	
	
	@PostMapping("/adduser")
	@ResponseStatus(HttpStatus.CREATED)
	public List<User> createUser(@Valid @RequestBody User user)
	{
		userService.createUser(user);
		return userService.getAllUserDetails();
	}
	
	
	@PutMapping("/updateuserdetails/{userid}")
	@ResponseStatus(HttpStatus.CREATED)
	public User updateUserDetails(@PathVariable("userid") Long userId,@Valid @RequestBody User user) throws UserNotFoundException
	{
		return userService.updateUserDetails(user,userId);
	}
	
	
	@DeleteMapping("/deleteuser/{userid}")
	public List<User> deleteUser(@PathVariable("userid")Long userId) throws UserNotFoundException
	{
		userService.deleteUser(userId);
		return userService.getAllUserDetails();
	}
	
	
}
