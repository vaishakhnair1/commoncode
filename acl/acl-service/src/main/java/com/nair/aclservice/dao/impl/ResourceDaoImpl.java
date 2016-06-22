package com.nair.aclservice.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nair.aclservice.constants.Queries;
import com.nair.aclservice.dao.IResourceDao;
import com.nair.aclservice.enums.Status;
import com.nair.aclservice.resource.models.Resource;

@Repository
public class ResourceDaoImpl implements IResourceDao{
	
	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Override
	public Resource getResourceById(String id) {
		try{
			Map<String, Object> map = jdbcTemplate.queryForMap(Queries.SELECT_RESOURCE_BY_ID, id, Status.ACTIVE.getValue());
			System.out.println(map);
			if(map != null && !map.isEmpty()){
				return mapResource(map);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Resource getResourceByName(String name) {
		try{
			Map<String, Object> map = jdbcTemplate.queryForMap(Queries.SELECT_RESOURCE_BY_NAME, name, Status.ACTIVE.getValue());
			System.out.println(map);
			if(map != null && !map.isEmpty()){
				return mapResource(map);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	private Resource mapResource(Map<String, Object> map) {
		Resource resource = new Resource();
		resource.setId(map.get("RESOURCE_ID").toString());
		resource.setResourceName(map.get("RESOURCE_NAME").toString());
		resource.setResourceDescription(map.get("RESOURCE_DESCRIPTION").toString());
		return resource;
	}

	@Override
	public boolean addResource(String resourceName, String resourceDescription) {
		try{
			jdbcTemplate.update(Queries.INSERT_RESOURCE, resourceName, resourceDescription, Status.ACTIVE.getValue());
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

}
