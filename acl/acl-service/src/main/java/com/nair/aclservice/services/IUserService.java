package com.nair.aclservice.services;

import com.nair.aclservice.user.models.User;

public interface IUserService {

	User getUserById(String id);
	
	User getUserByName(String name);
	
	boolean addUser(String userName);
	
}
