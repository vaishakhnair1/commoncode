package com.nair.aclservice.resource.models;

public class Resource {

	private String id;
	private String resourceName;
	private String resourceDescription;
	
	public String getResourceDescription() {
		return resourceDescription;
	}
	public void setResourceDescription(String resourceDescription) {
		this.resourceDescription = resourceDescription;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	@Override
	public String toString() {
		return "Resource [id=" + id + ", resourceName=" + resourceName + ", resourceDescription=" + resourceDescription
				+ "]";
	}
}
