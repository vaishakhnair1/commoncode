package com.nair.aclservice.services;

import com.nair.aclservice.roles.models.Roles;

public interface IRoleService {

	Roles getRoleById(String id);
	
	Roles getRoleByName(String roleName);
	
	boolean addRole(String roleName);
}
