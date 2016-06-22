package com.nair.aclservice.dao;

import com.nair.aclservice.resource.models.Resource;

public interface IResourceDao {

	Resource getResourceById(String id);
	
	Resource getResourceByName(String name);
	
	boolean addResource(String resourceName, String resourceDescription);

}
