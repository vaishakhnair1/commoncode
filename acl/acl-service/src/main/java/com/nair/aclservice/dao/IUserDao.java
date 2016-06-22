package com.nair.aclservice.dao;

import com.nair.aclservice.user.models.User;

public interface IUserDao {

	User getUserById(String id);
	
	User getUserByName(String name);
	
	boolean addUser(String userName);

}
