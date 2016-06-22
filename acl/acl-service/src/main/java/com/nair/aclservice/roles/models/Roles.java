package com.nair.aclservice.roles.models;

public class Roles {

	private String id;
	private String roleName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	@Override
	public String toString() {
		return "Roles [id=" + id + ", roleName=" + roleName + "]";
	}
}
