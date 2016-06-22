package com.nair.aclservice.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nair.aclservice.dao.IResourceDao;
import com.nair.aclservice.resource.models.Resource;
import com.nair.aclservice.services.IResourceService;

@Service
public class ResourceServiceImpl implements IResourceService {

	@Autowired
	private IResourceDao resourceDao;

	@Override
	public Resource getResourceById(String id) {
		return resourceDao.getResourceById(id);
	}

	@Override
	public Resource getResourceByName(String name) {
		return resourceDao.getResourceByName(name);
	}

	@Override
	public boolean addResource(String resourceName, String resourceDescription) {
		return resourceDao.addResource(resourceName, resourceDescription);
	}
	
	

}
