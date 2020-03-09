package com.zukalover.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zukalover.entity.User;
import com.zukalover.repository.UserRepository;

@Service
@Transactional
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public void saveUser(User user)
	{
		userRepository.save(user);
	}
	
	public User findUserById(Integer id)
	{
		return userRepository.getOne(id);
	}
	
	public void deleteUser(Integer id)
	{
		userRepository.deleteById(id);
	}
	
	public List<User> findAllUsers()
	{
		return userRepository.findAll();
	}
	
	public void updateUser(User user)
	{
		userRepository.save(user);
		
	}
	
	public User findByUsername(String username)
	{
		return userRepository.findByUsername(username);
	}
}
