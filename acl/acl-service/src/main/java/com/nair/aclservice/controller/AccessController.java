package com.nair.aclservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nair.aclservice.controller.Response.ApiCallStatus;
import com.nair.aclservice.enums.AccessLevels;
import com.nair.aclservice.services.IAccessService;

@RestController
@RequestMapping(value = "/access")
public class AccessController {

	@Autowired
	private IAccessService accessService;
	
	@RequestMapping(value = "/mapUserToRole", method = {RequestMethod.GET})
	public Response<Object> mapUserToRole(@RequestParam("userName") String userName, @RequestParam("roleName") String roleName){
		Response<Object> response = new Response<>();
		if(accessService.mapUserToRole(userName, roleName)){
			response.setStatus(ApiCallStatus.SUCCESS);
		}
		return response;
	}
	
	@RequestMapping(value = "/deleteUserRoleMapping", method = {RequestMethod.GET})
	public Response<Object> deleteUserRoleMapping(@RequestParam("userName") String userName, @RequestParam("roleName") String roleName){
		Response<Object> response = new Response<>();
		if(accessService.removeUserRoleMapping(userName, roleName)){
			response.setStatus(ApiCallStatus.SUCCESS);
		}
		return response;
	}
	
	@RequestMapping(value = "/addPermission", method = {RequestMethod.GET})
	public Response<Object> addPermission(@RequestParam("roleName") String roleName, @RequestParam("resourceName") String resourceName, @RequestParam("accessLevel") AccessLevels accessLevels){
		Response<Object> response = new Response<>();
		if(accessService.addPermisssion(roleName, resourceName, accessLevels)){
			response.setStatus(ApiCallStatus.SUCCESS);
		}
		return response;
	}
	
	@RequestMapping(value = "/checkPermission", method = {RequestMethod.GET})
	public Response<Object> checkPermission(@RequestParam("userName") String userName, @RequestParam("resourceName") String resourceName, @RequestParam("accessLevel") AccessLevels accessLevels){
		Response<Object> response = new Response<>();
		if(accessService.checkPermissions(userName, resourceName, accessLevels)){
			response.setStatus(ApiCallStatus.SUCCESS);
		}
		return response;
	}

	@RequestMapping(value = "/deletePermission", method = {RequestMethod.GET})
	public Response<Object> deletePermission(@RequestParam("roleName") String roleName, @RequestParam("resourceName") String resourceName, @RequestParam("accessLevel") AccessLevels accessLevels){
		Response<Object> response = new Response<>();
		if(accessService.removePermission(roleName, resourceName, accessLevels)){
			response.setStatus(ApiCallStatus.SUCCESS);
		}
		return response;
	}
	
	@RequestMapping(value = "/removeRole", method = {RequestMethod.GET})
	public Response<Object> removeRole(@RequestParam("roleName") String roleName){
		Response<Object> response = new Response<Object>();
		if(accessService.removeRole(roleName)){
			response.setStatus(ApiCallStatus.SUCCESS);
		}
		return response;
	}
}
