package com.cg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dao.UserRepository;
import com.cg.exception.UserNotFoundException;
import com.cg.model.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepo;

	@Override
	public List<User> getAllUserDetails() {
		
		return userRepo.findAll();
	}

	@Override
	public User getUserDetailsByUserId(Long userId) throws UserNotFoundException {
		Optional<User>  optuser=userRepo.findById(userId);
		if(optuser.isPresent()) {
			return optuser.get();
		}
		else
		{
			throw new UserNotFoundException("User not found for fiven userId");
		}
	}

	@Override
	public User createUser(User user) {
		return userRepo.save(user);
	}

	@Override
	public User updateUserDetails(User user, Long userId) throws UserNotFoundException {
		Optional<User> optuser=userRepo.findById(userId);
		if(optuser.isPresent())
		{
			User u=optuser.get();
			u.setUserId(user.getUserId());
			u.setUserName(user.getUserName());
			u.setFullName(user.getFullName());
			u.setMobileNo(user.getMobileNo());
			u.setMailId(user.getMailId());
			u.setGender(user.getGender());
			u.setAge(user.getAge());
			u.setPassword(user.getPassword());
			return userRepo.save(u);
			
		}
		else
		{
			throw new UserNotFoundException("User not found for given userId");
		}
	}

	@Override
	public void deleteUser(Long userId) throws UserNotFoundException {
		
		Optional<User> optbus=userRepo.findById(userId);
		if(optbus.isPresent()) {
			userRepo.deleteById(userId);
		}
		else 
		{
			throw new UserNotFoundException("User not found for fiven userId");
		}
		
	}

}
