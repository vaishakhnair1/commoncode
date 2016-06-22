package com.nair.aclservice.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nair.aclservice.constants.Queries;
import com.nair.aclservice.dao.IUserDao;
import com.nair.aclservice.enums.Status;
import com.nair.aclservice.user.models.User;

@Repository
public class UserDaoImpl implements IUserDao{
	
	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Override
	public User getUserById(String id) {
		try{
			Map<String, Object> map = jdbcTemplate.queryForMap(Queries.SELECT_USER_BY_ID, id, Status.ACTIVE.getValue());
			System.out.println(map);
			if(map != null && !map.isEmpty()){
				return mapUser(map);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User getUserByName(String name) {
		try{
			Map<String, Object> map = jdbcTemplate.queryForMap(Queries.SELECT_USER_BY_NAME, name, Status.ACTIVE.getValue());
			System.out.println(map);
			if(map != null && !map.isEmpty()){
				return mapUser(map);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	private User mapUser(Map<String, Object> map) {
		User user = new User();
		user.setId(map.get("USER_ID").toString());
		user.setUserName(map.get("USER_NAME").toString());
		return user;
	}

	@Override
	public boolean addUser(String username) {
		try{
			jdbcTemplate.update(Queries.INSERT_USER, username, Status.ACTIVE.getValue());
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

}
