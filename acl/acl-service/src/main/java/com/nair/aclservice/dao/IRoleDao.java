package com.nair.aclservice.dao;

import com.nair.aclservice.roles.models.Roles;

public interface IRoleDao {

	Roles getRoleById(String id);
	
	Roles getRoleByName(String roleName);
	
	boolean addRole(String roleName);
	
}
