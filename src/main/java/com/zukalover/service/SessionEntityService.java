package com.zukalover.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zukalover.entity.SessionEntity;
import com.zukalover.repository.SessionEntityRepository;

@Service
public class SessionEntityService {

	@Autowired
	SessionEntityRepository sessionEntityRepository;
	
	
	
	public SessionEntity findById(Integer id)
	{
		return sessionEntityRepository.getOne(id);
		
	}
	
	public void deleteSession(Integer id)
	{
		sessionEntityRepository.deleteById(id);
	}
	
	public void createSession(SessionEntity sessionEntity)
	{
		sessionEntityRepository.save(sessionEntity);
	}
}
