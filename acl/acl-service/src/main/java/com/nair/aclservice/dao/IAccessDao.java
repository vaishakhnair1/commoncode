package com.nair.aclservice.dao;

import java.util.List;

import com.nair.aclservice.enums.AccessLevels;

public interface IAccessDao {

	boolean mapUserToRole(String userId, String roleId);
	
	boolean removeUserRoleMapping(String userId, String roleId);
	
	boolean removeRole(String roleId);
	
	boolean addPermission(String roleId, String resourceId, AccessLevels accessLevels);
	
	boolean checkPermission(String roleId, String resourceId, AccessLevels accessLevels);
	
	boolean removePermission(String roleId, String resourceId, AccessLevels accessLevels);
	
	List<String> getRoles(String userId);
}
