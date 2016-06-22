package com.nair.aclservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nair.aclservice.controller.Response.ApiCallStatus;
import com.nair.aclservice.services.IUserService;
import com.nair.aclservice.user.models.User;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private IUserService userService;
	
	@RequestMapping(value = "/get", method = {RequestMethod.GET})
	public Response<User> get(@RequestParam("id") String id) {
		User user = userService.getUserById(id);
		Response<User> response = new Response<>();
		response.setStatus(ApiCallStatus.FAIL);
		if(user != null){
			response.setResponse(user);
			response.setStatus(ApiCallStatus.SUCCESS);
		}
		return response;
	}

	@RequestMapping(value = "/getByName", method = {RequestMethod.GET})
	public Response<User> getByName(@RequestParam("userName") String userName) {
		User user = userService.getUserByName(userName);
		Response<User> response = new Response<>();
		response.setStatus(ApiCallStatus.FAIL);
		if(user != null){
			response.setResponse(user);
			response.setStatus(ApiCallStatus.SUCCESS);
		}
		return response;
	}
	
	@RequestMapping(value = "/add", method = {RequestMethod.GET})
	public Response<Object> addUser(@RequestParam("userName") String userName){
		boolean processed = userService.addUser(userName);
		Response<Object> response = new Response<Object>();
		ApiCallStatus apiCallStatus = processed ? ApiCallStatus.SUCCESS : ApiCallStatus.FAIL;
		response.setStatus(apiCallStatus);
		return response;
	}
}
