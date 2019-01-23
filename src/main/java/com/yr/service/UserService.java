package com.yr.service;

import java.util.List;

import com.yr.entity.Page;
import com.yr.entity.User;

public interface UserService {

	public Boolean add(User user);
	
	public Boolean update(User user);
	
	public Boolean delete(Long id);
	
	public Page<User> select(Page<User> page);
	
	public List<User> select();
	
	public User get(Long id);
	
}
