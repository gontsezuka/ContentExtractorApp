package com.zukalover.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sessionentities")
public class SessionEntity {
	@Id
	private Integer id;
	private String username;
	
	
	
	public SessionEntity()
	{
		
	}
	
	public SessionEntity(Integer id,String username)
	{
		this.id=id;
		this.username=username;

	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


}
