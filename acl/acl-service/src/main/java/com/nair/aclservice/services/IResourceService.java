package com.nair.aclservice.services;

import com.nair.aclservice.resource.models.Resource;

public interface IResourceService {

	Resource getResourceById(String id);
	
	Resource getResourceByName(String name);
	
	boolean addResource(String resourceName, String resourceDescription);
	
}
