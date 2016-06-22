package com.nair.aclservice.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nair.aclservice.constants.Queries;
import com.nair.aclservice.dao.IAccessDao;
import com.nair.aclservice.enums.AccessLevels;
import com.nair.aclservice.enums.Status;

@Repository
public class AccessDaoImpl implements IAccessDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean mapUserToRole(String userId, String roleId) {
		try{
			jdbcTemplate.update(Queries.MAP_USER_ROLE, userId, roleId, Status.ACTIVE.getValue());
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean removeUserRoleMapping(String userId, String roleId) {
		try{
			jdbcTemplate.update(Queries.DELETE_USER_ROLE_MAPPING, userId, roleId);
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean removeRole(String roleId) {
		try{
			jdbcTemplate.update(Queries.DELETE_ROLE, roleId);
			jdbcTemplate.update(Queries.DELETE_ROLE_MAPPING, roleId);
			jdbcTemplate.update(Queries.DELETE_ROLE_PERMISSIONS, roleId);
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean addPermission(String roleId, String resourceId, AccessLevels accessLevels) {
		try{
			jdbcTemplate.update(Queries.ADD_PERMISSIONS, roleId, accessLevels.getValue(), resourceId, Status.ACTIVE.getValue());
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean checkPermission(String roleId, String resourceId, AccessLevels accessLevels) {
		try{
			Map<String, Object> map = jdbcTemplate.queryForMap(Queries.CHECK_PERMISSIONS, roleId, resourceId, accessLevels.getValue(), Status.ACTIVE.getValue());
			if(map != null && !map.isEmpty()){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean removePermission(String roleId, String resourceId, AccessLevels accessLevels) {
		try{
			jdbcTemplate.update(Queries.DELETE_PERMISSION, roleId, resourceId, accessLevels.getValue(), Status.ACTIVE.getValue());
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<String> getRoles(String userId) {
		try{
			List<Map<String, Object>> list = jdbcTemplate.queryForList(Queries.GET_USER_ROLES, userId, Status.ACTIVE.getValue());
			if(list != null && !list.isEmpty()){
				List<String> result = new ArrayList<>();
				for(Map<String, Object> map : list){
					result.add(map.get("ROLE_ID").toString());
				}
				return result;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return Collections.emptyList();
	}
}
