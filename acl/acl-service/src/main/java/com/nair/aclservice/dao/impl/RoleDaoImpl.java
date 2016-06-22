package com.nair.aclservice.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nair.aclservice.constants.Queries;
import com.nair.aclservice.dao.IRoleDao;
import com.nair.aclservice.enums.Status;
import com.nair.aclservice.roles.models.Roles;

@Repository
public class RoleDaoImpl implements IRoleDao {

	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Roles getRoleById(String id) {
		try{
			Map<String, Object> map = jdbcTemplate.queryForMap(Queries.SELECT_ROLE_BY_ID, id, Status.ACTIVE.getValue());
			if(map != null && !map.isEmpty()){
				return mapRole(map);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	private Roles mapRole(Map<String, Object> map) {
		Roles role = new Roles();
		role.setId(map.get("ROLE_ID").toString());
		role.setRoleName(map.get("ROLE_NAME").toString());
		return role;
	}

	@Override
	public Roles getRoleByName(String roleName) {
		try{
			Map<String, Object> map = jdbcTemplate.queryForMap(Queries.SELECT_ROLE_BY_NAME, roleName, Status.ACTIVE.getValue());
			if(map != null && !map.isEmpty()){
				return mapRole(map);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean addRole(String roleName) {
		try{
			jdbcTemplate.update(Queries.INSERT_ROLE, roleName, Status.ACTIVE.getValue());
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

}
