package com.yr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yr.entity.Page;
import com.yr.entity.User;
import com.yr.h_dao.UserDao;
import com.yr.service.UserService;

@Transactional
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Transactional
	@Override
	public Boolean add(User user) {
		try {
			userDao.add(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Transactional
	@Override
	public Boolean update(User user) {
		try {
			userDao.update(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Transactional
	@Override
	public Boolean delete(Long id) {
		try {
			userDao.delete(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Page<User> select(Page<User> page) {
		page = userDao.select(page);
		return page;
	}
	
	@Override
	public User get(Long id) {
		User ListiId = userDao.get(id);
		return ListiId;
	}

	@Override
	public List<User> select() {
		List<User> listUser = userDao.select();
		return listUser;
	}

}
