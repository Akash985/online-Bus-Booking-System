package com.cg;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.dao.UserRepository;

import com.cg.exception.UserNotFoundException;

import com.cg.model.User;
import com.cg.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceApplicationTests {

	@Autowired
	UserService userService;
	
	@MockBean
	UserRepository userRepo;
	
	User user;
	List<User> list;
	
	@Before
	public void setUp() throws Exception {
		user= new User(100L,"atharva","Atharva Patil",9941775484L,"atharva@gmail.com","M",23,"qwerty@123");
		list = new ArrayList<User>();
		list.add(user);
	}
	
	@Test
	public void getAllUsersTest()
	{

		when(userRepo.findAll()).thenReturn(Stream.
				of(new User(100L,"atharva","Atharva Patil",9941775484L,"atharva@gmail.com","M",23,"qwerty@123")).collect(Collectors.toList()));
		assertEquals(1,userService.getAllUserDetails().size());
	}
	
	@Test
	public void TestUserByIdTest() throws UserNotFoundException
	{
		Long userId=100L;
		Optional<User> opt=Optional.of(user);
		when(userRepo.findById(userId)).thenReturn(opt);
		assertEquals(user, userService.getUserDetailsByUserId(userId));
		
	}
	
	
	
	@Test
	public void TestAddUser() 
	{
		User user= new User(100L,"atharva","Atharva Patil",9941775484L,"atharva@gmail.com","M",23,"qwerty@123");
		when(userRepo.save(user)).thenReturn(user);
		assertEquals(user, userService.createUser(user));
		
	}
	
	
	@Test(expected = UserNotFoundException.class)
	public void testUpdateUserById_Success() throws UserNotFoundException {
		Long userId=100L;
		User user= new User(100L,"atharva","Atharva Patil",9941775484L,"atharva@gmail.com","M",23,"qwerty@123");
		when(userRepo.existsById(user.getUserId())).thenReturn(true);
		when(userRepo.save(user)).thenReturn(user);
		assertEquals(user, userService.updateUserDetails(user,userId));
	}
	
	@Test(expected = UserNotFoundException.class)
	public void testDeleteUser() throws UserNotFoundException{
		Long userId=100L;
		User user= new User(100L,"atharva","Atharva Patil",9941775484L,"atharva@gmail.com","M",23,"qwerty@123");
		userService.deleteUser(userId);
		verify(userRepo,times(1)).delete(user);
		
	}


}
