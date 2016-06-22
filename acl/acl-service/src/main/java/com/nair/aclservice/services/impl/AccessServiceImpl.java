package com.nair.aclservice.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nair.aclservice.dao.IAccessDao;
import com.nair.aclservice.enums.AccessLevels;
import com.nair.aclservice.resource.models.Resource;
import com.nair.aclservice.roles.models.Roles;
import com.nair.aclservice.services.IAccessService;
import com.nair.aclservice.services.IResourceService;
import com.nair.aclservice.services.IRoleService;
import com.nair.aclservice.services.IUserService;
import com.nair.aclservice.user.models.User;

@Service
public class AccessServiceImpl implements IAccessService {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private IResourceService resourceService;
	
	@Autowired
	private IAccessDao accessDao;
	
	@Override
	public boolean mapUserToRole(String userName, String roleName) {
		User user = userService.getUserByName(userName);
		Roles role = roleService.getRoleByName(roleName);
		if(user != null && role != null){
			return accessDao.mapUserToRole(user.getId(), role.getId());
		}
		return false;
	}

	@Override
	public boolean removeUserRoleMapping(String userName, String roleName) {
		User user = userService.getUserByName(userName);
		Roles role = roleService.getRoleByName(roleName);
		if(user != null && role != null){
			return accessDao.removeUserRoleMapping(user.getId(), role.getId());
		}
		return false;
	}

	@Override
	public boolean addPermisssion(String roleName, String resourceName, AccessLevels accessLevels) {
		Roles role = roleService.getRoleByName(roleName);
		Resource resource = resourceService.getResourceByName(resourceName);
		if(resource != null && role != null){
			return accessDao.addPermission(role.getId(), resource.getId(), accessLevels);
		}
		return false;
	}

	@Override
	public boolean checkPermissions(String userName, String reourceName, AccessLevels accessLevels) {
		User user = userService.getUserByName(userName);
		Resource resource = resourceService.getResourceByName(reourceName);
		if(user != null && resource != null){
			List<String> roles = accessDao.getRoles(user.getId());
			for(String roleId : roles){
				if(accessDao.checkPermission(roleId, resource.getId(), accessLevels)){
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean removePermission(String roleName, String resourceName, AccessLevels accessLevels) {
		Roles roles = roleService.getRoleByName(roleName);
		Resource resource = resourceService.getResourceByName(resourceName);
		if(roles != null && resource != null){
			return accessDao.removePermission(roles.getId(), resource.getId(), accessLevels);
		}
		return false;
	}

	@Override
	public boolean removeRole(String roleName) {
		Roles role = roleService.getRoleByName(roleName);
		if(role != null){
			return accessDao.removeRole(role.getId());
		}
		return false;
	}

}
