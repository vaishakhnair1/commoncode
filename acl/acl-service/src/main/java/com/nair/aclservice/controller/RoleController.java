package com.nair.aclservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nair.aclservice.controller.Response.ApiCallStatus;
import com.nair.aclservice.roles.models.Roles;
import com.nair.aclservice.services.IRoleService;

@RestController
@RequestMapping(value = "/role")
public class RoleController {

	@Autowired
	private IRoleService roleService;
	
	@RequestMapping(value = "/get" , method = {RequestMethod.GET})
	public Response<Roles> get(@RequestParam("id") String id){
		Roles role = roleService.getRoleById(id);
		Response<Roles> response = new Response<>();
		response.setStatus(ApiCallStatus.FAIL);
		if(role != null){
			response.setResponse(role);
			response.setStatus(ApiCallStatus.SUCCESS);
		}
		return response;
	}

	@RequestMapping(value = "/getByName" , method = {RequestMethod.GET})
	public Response<Roles> getByName(@RequestParam("roleName") String roleName){
		Roles role = roleService.getRoleByName(roleName);
		Response<Roles> response = new Response<>();
		response.setStatus(ApiCallStatus.FAIL);
		if(role != null){
			response.setResponse(role);
			response.setStatus(ApiCallStatus.SUCCESS);
		}
		return response;
	}

	
	@RequestMapping(value = "/add", method = {RequestMethod.GET})
	public Response<Object> addRole(@RequestParam("roleName") String roleName){
		boolean processed = roleService.addRole(roleName);
		Response<Object> response = new Response<Object>();
		ApiCallStatus apiCallStatus = processed ? ApiCallStatus.SUCCESS : ApiCallStatus.FAIL;
		response.setStatus(apiCallStatus);
		return response;
	}
	
	@RequestMapping(value = "/delete", method = {RequestMethod.GET})
	public Response<Object> deleteRole(@RequestParam("id") String id){
		return null;
	}
}
