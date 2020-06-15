package com.cg.service;
import java.util.List;

import com.cg.exception.UserNotFoundException;
import com.cg.model.User;

public interface UserService {
	
	public List<User> getAllUserDetails();
	public User getUserDetailsByUserId(Long userId) throws UserNotFoundException;
	public User createUser(User user);
	public User updateUserDetails(User user,Long userId) throws UserNotFoundException;
	public void deleteUser(Long userId) throws UserNotFoundException;

}
