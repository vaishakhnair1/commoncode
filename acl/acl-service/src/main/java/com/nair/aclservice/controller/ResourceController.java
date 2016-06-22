package com.nair.aclservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nair.aclservice.controller.Response.ApiCallStatus;
import com.nair.aclservice.resource.models.Resource;
import com.nair.aclservice.services.IResourceService;

@RestController
@RequestMapping(value = "/resource")
public class ResourceController {

	@Autowired
	private IResourceService resourceService;
	
	@RequestMapping(value = "/get", method = {RequestMethod.GET})
	public Response<Resource> get(@RequestParam("id") String id) {
		Resource user = resourceService.getResourceById(id);
		Response<Resource> response = new Response<>();
		response.setStatus(ApiCallStatus.FAIL);
		if(user != null){
			response.setResponse(user);
			response.setStatus(ApiCallStatus.SUCCESS);
		}
		return response;
	}

	@RequestMapping(value = "/getByName", method = {RequestMethod.GET})
	public Response<Resource> getByName(@RequestParam("resourceName") String resourceName) {
		Resource user = resourceService.getResourceByName(resourceName);
		Response<Resource> response = new Response<>();
		response.setStatus(ApiCallStatus.FAIL);
		if(user != null){
			response.setResponse(user);
			response.setStatus(ApiCallStatus.SUCCESS);
		}
		return response;
	}
	
	@RequestMapping(value = "/add", method = {RequestMethod.GET})
	public Response<Object> addUser(@RequestParam("resourceName") String resourceName, @RequestParam("resourceDescription") String resourceDescription){
		boolean processed = resourceService.addResource(resourceName, resourceDescription);
		Response<Object> response = new Response<Object>();
		ApiCallStatus apiCallStatus = processed ? ApiCallStatus.SUCCESS : ApiCallStatus.FAIL;
		response.setStatus(apiCallStatus);
		return response;
	}
}
