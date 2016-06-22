package com.nair.aclservice.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nair.aclservice.dao.IRoleDao;
import com.nair.aclservice.roles.models.Roles;
import com.nair.aclservice.services.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService{

	@Autowired
	private IRoleDao roleDao;
	
	@Override
	public Roles getRoleById(String id) {
		return roleDao.getRoleById(id);
	}

	@Override
	public Roles getRoleByName(String roleName) {
		return roleDao.getRoleByName(roleName);
	}

	@Override
	public boolean addRole(String roleName) {
		return roleDao.addRole(roleName);
	}

}
