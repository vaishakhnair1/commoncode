package com.nair.aclservice.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nair.aclservice.dao.IUserDao;
import com.nair.aclservice.services.IUserService;
import com.nair.aclservice.user.models.User;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;
	
	@Override
	public User getUserById(String id) {
		return userDao.getUserById(id);
	}

	@Override
	public User getUserByName(String name) {
		return userDao.getUserByName(name);
	}

	@Override
	public boolean addUser(String userName) {
		return userDao.addUser(userName);
	}

}
