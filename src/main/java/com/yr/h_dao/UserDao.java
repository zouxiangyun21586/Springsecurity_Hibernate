package com.yr.h_dao;

import java.util.List;

import com.yr.entity.Page;
import com.yr.entity.User;

public interface UserDao {
	public void add(User user);
	
	public void update(User user);
	
	public void delete(Long id);
	
	public Page<User> select(Page<User> page);
	
	public List<User> select();
	
	public User get(Long id);
	
	public User getUserByName(String name);
	
}	
