package com.nair.aclservice.services;

import com.nair.aclservice.enums.AccessLevels;

public interface IAccessService {

	boolean mapUserToRole(String userName, String roleName);
	
	boolean removeUserRoleMapping(String userName, String roleName);
	
	boolean addPermisssion(String roleName, String resource, AccessLevels accessLevels);
	
	boolean checkPermissions(String userName, String reource, AccessLevels accessLevels);
	
	boolean removePermission(String roleName, String resourceName, AccessLevels accessLevels);
	
	boolean removeRole(String roleName);
	
}
