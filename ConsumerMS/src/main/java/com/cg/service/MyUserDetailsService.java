package com.cg.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cg.dto.MyUserDetails;
import com.cg.dto.User;


@Service
public class MyUserDetailsService implements UserDetailsService {

	
	@Autowired
	com.cg.repository.UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username)
				.orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
		
		return new MyUserDetails(user);
	}

}
